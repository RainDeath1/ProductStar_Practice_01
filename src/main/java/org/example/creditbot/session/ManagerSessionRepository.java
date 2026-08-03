package org.example.creditbot.session;

import java.util.HashSet;
import java.util.Set;

public class ManagerSessionRepository {

    private final Set<Long> managers = new HashSet<>();

    public void addManager(long chatId) {
        managers.add(chatId);
    }

    public boolean isManager(long chatId) {
        return managers.contains(chatId);
    }

    public void removeManager(long chatId) {
        managers.remove(chatId);
    }
}