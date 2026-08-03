package org.example.creditbot.service;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.PaymentType;
import org.example.creditbot.repository.RequestRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsService {

    private final RequestRepository repository;

    public AnalyticsService(RequestRepository repository) {
        this.repository = repository;
    }

    public int getTotalRequests() {
        return repository.findAll().size();
    }

    public double getAverageAmount() {

        List<CreditRequest> requests = repository.findAll();

        if (requests.isEmpty()) {
            return 0;
        }

        double sum = 0;

        for (CreditRequest request : requests) {
            sum += request.getAmount();
        }

        return sum / requests.size();
    }

    public PaymentType getMostPopularPaymentType() {

        List<CreditRequest> requests = repository.findAll();

        Map<PaymentType, Integer> statistics = new HashMap<>();

        for (CreditRequest request : requests) {

            statistics.put(
                    request.getPaymentType(),
                    statistics.getOrDefault(request.getPaymentType(), 0) + 1
            );

        }

        PaymentType result = null;
        int max = 0;

        for (Map.Entry<PaymentType, Integer> entry : statistics.entrySet()) {

            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }

        }

        return result;
    }

}