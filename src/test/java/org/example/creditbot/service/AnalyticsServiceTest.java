package org.example.creditbot.service;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.PaymentType;
import org.example.creditbot.repository.RequestRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsServiceTest {


    @Test
    void shouldCalculateAnalytics() {


        // given

        RequestRepository repository =
                new RequestRepository();


        AnalyticsService analyticsService =
                new AnalyticsService(repository);



        repository.save(
                new CreditRequest(
                        1L,
                        100000,
                        6,
                        20,
                        PaymentType.ANNUITY
                )
        );


        repository.save(
                new CreditRequest(
                        2L,
                        200000,
                        12,
                        18,
                        PaymentType.ANNUITY
                )
        );


        repository.save(
                new CreditRequest(
                        3L,
                        300000,
                        24,
                        15,
                        PaymentType.DIFFERENTIAL
                )
        );



        // when

        int totalRequests =
                analyticsService.getTotalRequests();


        double averageAmount =
                analyticsService.getAverageAmount();


        PaymentType popularType =
                analyticsService.getMostPopularPaymentType();



        // then


        // Всего запросов должно быть 3

        assertEquals(
                3,
                totalRequests
        );


        // Средняя сумма:
        // (100000 + 200000 + 300000) / 3 = 200000

        assertEquals(
                200000,
                averageAmount,
                0.01
        );


        // ANNUITY встречается 2 раза,
        // DIFFERENTIAL 1 раз

        assertEquals(
                PaymentType.ANNUITY,
                popularType
        );
    }
}