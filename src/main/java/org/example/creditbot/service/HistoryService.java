package org.example.creditbot.service;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.repository.RequestRepository;

import java.util.List;

public class HistoryService {

    private final RequestRepository repository;

    public HistoryService(RequestRepository repository) {
        this.repository = repository;
    }

    public List<CreditRequest> getHistory(long chatId) {
        return repository.findByChatId(chatId);
    }

    private String formatRequest(CreditRequest request) {
        return String.format("""
Сумма: %.2f
Срок: %d мес.
Ставка: %.2f%%
Тип: %s
Дата: %s
""",
                request.getAmount(),
                request.getMonths(),
                request.getAnnualRate(),
                request.getPaymentType().getTitle(),
                request.getCreatedAt());
    }

}