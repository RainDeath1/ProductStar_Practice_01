package org.example.creditbot.command;

public class StartCommand implements Command {

    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public String execute(long chatId, String[] args) {

        return """
                Добро пожаловать!

                Я помогу рассчитать график кредита.

                Доступные команды:

                /calculate
                /history
                /manager
                """;
    }
}