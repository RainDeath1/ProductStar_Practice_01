package org.example.creditbot.handler;

import org.example.creditbot.config.BotConfig;
import org.example.creditbot.session.LoginSession;
import org.example.creditbot.session.LoginSessionRepository;
import org.example.creditbot.session.LoginState;
import org.example.creditbot.session.ManagerSessionRepository;

public class LoginHandler {

    private final LoginSessionRepository loginSessionRepository;
    private final ManagerSessionRepository managerSessionRepository;
    private final BotConfig config;


    public LoginHandler(
            LoginSessionRepository loginSessionRepository,
            ManagerSessionRepository managerSessionRepository,
            BotConfig config
    ) {

        this.loginSessionRepository = loginSessionRepository;
        this.managerSessionRepository = managerSessionRepository;
        this.config = config;
    }



    public String handle(long chatId, String text) {


        LoginSession session =
                loginSessionRepository.getSession(chatId);



        switch (session.getState()) {


            case WAIT_LOGIN -> {

                session.setLogin(text);
                session.setState(LoginState.WAIT_PASSWORD);


                return """
                        Введите пароль:
                        """;
            }



            case WAIT_PASSWORD -> {


                if(config.getManagerLogin()
                        .equals(session.getLogin())

                        &&

                        config.getManagerPassword()
                                .equals(text)) {



                    managerSessionRepository.addManager(chatId);


                    loginSessionRepository.clear(chatId);



                    return """
                            ✅ Авторизация успешна!

                            Теперь доступна команда:

                            /manager
                            """;
                }



                loginSessionRepository.clear(chatId);



                return """
                        ❌ Неверный логин или пароль.

                        Попробуйте снова через /login
                        """;
            }



            default -> {
                return "";
            }
        }
    }
}