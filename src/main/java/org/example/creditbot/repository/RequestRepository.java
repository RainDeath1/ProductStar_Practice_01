package org.example.creditbot.repository;

import org.example.creditbot.model.CreditRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestRepository {

    private final Map<Long, List<CreditRequest>> requests = new HashMap<>();

    public void save(CreditRequest request) {

        requests
                .computeIfAbsent(request.getChatId(), id -> new ArrayList<>())
                .add(request);

    }

    public List<CreditRequest> findByChatId(long chatId) {

        return requests.getOrDefault(chatId, new ArrayList<>());

    }

    public List<CreditRequest> findAll() {

        List<CreditRequest> result = new ArrayList<>();

        for (List<CreditRequest> list : requests.values()) {
            result.addAll(list);
        }

        return result;
    }
}