package org.example.creditbot.service;

import org.example.creditbot.factory.CalculatorFactory;
import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.Payment;
import org.example.creditbot.model.PaymentType;
import org.example.creditbot.repository.RequestRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditServiceTest {


    @Test
    void shouldCalculateCreditAndSaveRequest() {


        // given

        RequestRepository repository =
                new RequestRepository();


        CalculatorFactory factory =
                new CalculatorFactory();


        CreditService creditService =
                new CreditService(
                        factory,
                        repository
                );


        CreditRequest request =
                new CreditRequest(
                        123L,
                        100000,
                        6,
                        20,
                        PaymentType.ANNUITY
                );



        // when

        List<Payment> payments =
                creditService.calculate(request);



        // then


        // Проверяем, что график создан

        assertNotNull(payments);



        // Должно быть 6 платежей

        assertEquals(
                6,
                payments.size()
        );



        // Проверяем, что запрос сохранился

        List<CreditRequest> savedRequests =
                repository.findByChatId(123L);


        assertEquals(
                1,
                savedRequests.size()
        );



        assertEquals(
                100000,
                savedRequests.get(0).getAmount()
        );
    }
}