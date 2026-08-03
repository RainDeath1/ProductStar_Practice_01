package org.example.creditbot.command;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandDispatcher(Command... commands){
        for (Command command : commands){
            this.commands.put(command.getCommand(), command);
        }
    }

    public String dispatch(long chatId, String text){
        String[] parts = text.split("\\s+");

        Command command = commands.get(parts[0]);

        if (command == null){
            return "Неизвестная команда";
        }
        return command.execute(chatId, parts);
    }
}