package org.example.creditbot.command;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.service.HistoryService;

import java.util.List;

public class HistoryCommand implements Command {

    private final HistoryService historyService;

    public HistoryCommand(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public String getCommand() {
        return "/history";
    }

    @Override
    public String execute(long chatId, String[] args) {

        List<CreditRequest> history = historyService.getHistory(chatId);

        if (history.isEmpty()) {
            return "История запросов пуста.";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("📋 История запросов\n\n");

        for (CreditRequest request : history) {

            builder.append(formatRequest(request))
                    .append("\n")
                    .append("-----------------\n\n");
        }

        return builder.toString();
    }


    private String formatRequest(CreditRequest request) {

        return String.format("""
                
                💰 Сумма: %.2f
                📆 Срок: %d мес.
                📈 Ставка: %.2f%%
                💳 Тип платежа: %s
                🕒 Дата: %s
                
                """,
                request.getAmount(),
                request.getMonths(),
                request.getAnnualRate(),
                request.getPaymentType(),
                request.getCreatedAt()
        );
    }
}