package org.example.creditbot.command;

import org.example.creditbot.session.LoginSession;
import org.example.creditbot.session.LoginSessionRepository;
import org.example.creditbot.session.LoginState;

public class LoginCommand implements Command{

    private final LoginSessionRepository loginSessionRepository;

    public LoginCommand(LoginSessionRepository loginSessionRepository){
        this.loginSessionRepository=loginSessionRepository;
    }

    @Override
    public String getCommand(){
        return "/login";
    }

    @Override
    public String execute(long chatId,String[] args){
        LoginSession session =
                loginSessionRepository.getSession(chatId);

        session.setState(LoginState.WAIT_LOGIN);

        return """
                Авторизация менеджера
                
                Введите логин:
                
                """;
    }
}