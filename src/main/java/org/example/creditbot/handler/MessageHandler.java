package org.example.creditbot.handler;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.Payment;
import org.example.creditbot.model.PaymentType;
import org.example.creditbot.service.CreditService;
import org.example.creditbot.session.SessionRepository;
import org.example.creditbot.session.UserSession;
import org.example.creditbot.session.UserState;

import java.util.List;

public class MessageHandler {

    private final SessionRepository sessionRepository;
    private final CreditService creditService;

    public MessageHandler(SessionRepository sessionRepository,
                          CreditService creditService) {

        this.sessionRepository = sessionRepository;
        this.creditService = creditService;
    }

    public String handle(long chatId, String text) {

        text = text.trim();

        UserSession session = sessionRepository.getSession(chatId);

        switch (session.getState()) {

            case WAIT_AMOUNT:
                return handleAmount(session, text);

            case WAIT_MONTHS:
                return handleMonths(session, text);

            case WAIT_RATE:
                return handleRate(session, text);

            case WAIT_PAYMENT_TYPE:
                return handlePaymentType(chatId, session, text);

            default:
                return """
                        Не понимаю сообщение.

                        Используйте команду /calculate
                        """;
        }
    }

    private String handleAmount(UserSession session, String text) {

        try {

            double amount = Double.parseDouble(text);

            if (amount <= 0) {
                return """
                        ❌ Сумма кредита должна быть больше 0.

                        Повторите ввод:
                        """;
            }

            if (amount > 1_000_000_000) {
                return """
                        ❌ Максимальная сумма кредита — 1 000 000 000.

                        Повторите ввод:
                        """;
            }

            session.setAmount(amount);
            session.setState(UserState.WAIT_MONTHS);

            return """
                    ✅ Сумма сохранена.

                    Введите срок кредита (в месяцах):
                    """;

        } catch (NumberFormatException e) {

            return """
                    ❌ Некорректная сумма кредита.

                    Пример:

                    1000000
                    """;
        }
    }

    private String handleMonths(UserSession session, String text) {

        try {

            int months = Integer.parseInt(text);

            if (months < 1 || months > 600) {
                return """
                        ❌ Срок кредита должен быть от 1 до 600 месяцев.

                        Повторите ввод:
                        """;
            }

            session.setMonths(months);
            session.setState(UserState.WAIT_RATE);

            return """
                    ✅ Срок сохранен.

                    Введите годовую процентную ставку:
                    """;

        } catch (NumberFormatException e) {

            return """
                    ❌ Некорректный срок кредита.

                    Пример:

                    12
                    """;
        }
    }

    private String handleRate(UserSession session, String text) {

        try {

            double rate = Double.parseDouble(text);

            if (rate <= 0 || rate > 100) {
                return """
                        ❌ Процентная ставка должна быть от 0.01 до 100%.

                        Повторите ввод:
                        """;
            }

            session.setAnnualRate(rate);
            session.setState(UserState.WAIT_PAYMENT_TYPE);

            return """
                    ✅ Ставка сохранена.

                    Выберите тип платежа:

                    1 - Аннуитетный

                    2 - Дифференцированный
                    """;

        } catch (NumberFormatException e) {

            return """
                    ❌ Некорректная процентная ставка.

                    Пример:

                    15.5
                    """;
        }
    }

    private String handlePaymentType(long chatId,
                                     UserSession session,
                                     String text) {

        PaymentType paymentType;

        switch (text) {

            case "1":
                paymentType = PaymentType.ANNUITY;
                break;

            case "2":
                paymentType = PaymentType.DIFFERENTIAL;
                break;

            default:
                return """
                        ❌ Неверный выбор.

                        Выберите:

                        1 - Аннуитетный

                        2 - Дифференцированный
                        """;
        }

        session.setPaymentType(paymentType);

        CreditRequest request = new CreditRequest(
                chatId,
                session.getAmount(),
                session.getMonths(),
                session.getAnnualRate(),
                paymentType
        );

        List<Payment> payments = creditService.calculate(request);

        sessionRepository.clearSession(chatId);

        return buildPaymentSchedule(payments);
    }

    private String buildPaymentSchedule(List<Payment> payments) {

        StringBuilder builder = new StringBuilder();

        builder.append("📅 График платежей\n\n");

        for (Payment payment : payments) {

            builder.append("Месяц: ")
                    .append(payment.getMonth())
                    .append("\n");

            builder.append("💰 Платеж: ")
                    .append(String.format("%.2f", payment.getTotalPayment()))
                    .append("\n");

            builder.append("🏦 Основной долг: ")
                    .append(String.format("%.2f", payment.getPrincipal()))
                    .append("\n");

            builder.append("📈 Проценты: ")
                    .append(String.format("%.2f", payment.getInterest()))
                    .append("\n");

            builder.append("📉 Остаток: ")
                    .append(String.format("%.2f", payment.getRemainingDebt()))
                    .append("\n\n");

            builder.append("-------------------\n\n");
        }

        return builder.toString();
    }
}