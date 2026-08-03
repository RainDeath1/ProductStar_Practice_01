package org.example.creditbot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    String getCommand();

    String execute(long chatId, String[] args);
}