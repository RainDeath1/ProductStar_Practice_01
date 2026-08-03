package org.example.creditbot.command;

import org.example.creditbot.session.SessionRepository;
import org.example.creditbot.session.UserSession;
import org.example.creditbot.session.UserSession;
import org.example.creditbot.session.UserState;

public class CalculateCommand implements Command{
    private final SessionRepository sessionRepository;

    public CalculateCommand(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    @Override
    public String getCommand(){
        return "/calculate";
    }

    @Override
    public String execute(long chatId,String[] args){
        UserSession session = sessionRepository.getSession(chatId);

        session.setState(UserState.WAIT_AMOUNT);

        return """
                Расчет кредита
                
                Введите сумму кредита:
                """;
    }
}