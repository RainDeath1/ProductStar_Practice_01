package org.example.creditbot.util;

public interface MessageSender {

    void send(long chatId, String text);

}