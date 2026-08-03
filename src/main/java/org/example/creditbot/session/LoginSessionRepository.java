package org.example.creditbot.session;

import java.util.HashMap;
import java.util.Map;

public class LoginSessionRepository {

    private final Map<Long, LoginSession> sessions = new HashMap<>();


    public LoginSession getSession(long chatId) {

        return sessions.computeIfAbsent(
                chatId,
                id -> new LoginSession()
        );
    }


    public void clear(long chatId) {

        sessions.remove(chatId);
    }
}