package org.example.creditbot.session;

import java.util.HashMap;
import java.util.Map;

public class SessionRepository {

    private final Map<Long, UserSession> sessions = new HashMap<>();

    public UserSession getSession(long chatId) {

        return sessions.computeIfAbsent(chatId, id -> new UserSession());

    }

    public void clearSession(long chatId) {

        sessions.remove(chatId);

    }

}