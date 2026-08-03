package org.example.creditbot.calculator;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.Payment;
import org.example.creditbot.model.PaymentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DifferentialCalculatorTest {


    @Test
    void shouldCalculateDifferentialPaymentSchedule() {


        // given
        CreditRequest request = new CreditRequest(
                1L,
                100000,
                6,
                20,
                PaymentType.DIFFERENTIAL
        );


        DifferentialCalculator calculator =
                new DifferentialCalculator();



        // when
        List<Payment> payments =
                calculator.calculate(request);



        // then


        // Должно быть 6 платежей
        assertEquals(
                6,
                payments.size()
        );



        Payment firstPayment =
                payments.get(0);


        Payment lastPayment =
                payments.get(
                        payments.size() - 1
                );



        // Первый платеж должен быть больше последнего
        assertTrue(
                firstPayment.getTotalPayment()
                        >
                        lastPayment.getTotalPayment()
        );



        // Основной долг каждый месяц одинаковый
        double principal =
                payments.get(0).getPrincipal();


        for (Payment payment : payments) {

            assertEquals(
                    principal,
                    payment.getPrincipal(),
                    0.01
            );
        }



        // После последнего платежа долг закрыт

        assertEquals(
                0,
                lastPayment.getRemainingDebt(),
                0.01
        );
    }
}