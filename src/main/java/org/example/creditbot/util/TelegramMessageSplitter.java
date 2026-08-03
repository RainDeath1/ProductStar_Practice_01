package org.example.creditbot.util;

import java.util.ArrayList;
import java.util.List;

public class TelegramMessageSplitter {

    private static final int MAX_MESSAGE_LENGTH = 4000;

    public List<String> split(String text) {

        List<String> messages = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return messages;
        }

        StringBuilder currentMessage = new StringBuilder();

        for (String line : text.split("\n")) {

            if (currentMessage.length() + line.length() + 1 > MAX_MESSAGE_LENGTH) {

                messages.add(currentMessage.toString());
                currentMessage.setLength(0);
            }

            currentMessage
                    .append(line)
                    .append("\n");
        }

        if (!currentMessage.isEmpty()) {
            messages.add(currentMessage.toString());
        }

        return messages;
    }
}