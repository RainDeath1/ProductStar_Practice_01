package org.example.creditbot.calculator;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.PaymentType;
import org.example.creditbot.model.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnuityCalculatorTest {


    @Test
    void shouldCalculateAnnuityPaymentSchedule() {


        // given
        CreditRequest request = new CreditRequest(
                1L,
                100000,
                6,
                20,
                PaymentType.ANNUITY
        );


        AnnuityCalculator calculator = new AnnuityCalculator();


        // when
        List<Payment> payments =
                calculator.calculate(request);



        // then

        // Должно быть 6 платежей
        assertEquals(
                6,
                payments.size()
        );


        // Первый платеж существует и больше 0
        Payment firstPayment =
                payments.get(0);


        assertTrue(
                firstPayment.getTotalPayment() > 0
        );


        assertTrue(
                firstPayment.getInterest() > 0
        );


        assertTrue(
                firstPayment.getPrincipal() > 0
        );



        // Последний платеж должен закрыть кредит

        Payment lastPayment =
                payments.get(
                        payments.size() - 1
                );


        assertEquals(
                0,
                lastPayment.getRemainingDebt(),
                0.01
        );
    }
}