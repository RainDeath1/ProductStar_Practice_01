package org.example.creditbot.session;

public class LoginSession {

    private LoginState state = LoginState.NONE;

    private String login;


    public LoginState getState() {
        return state;
    }


    public void setState(LoginState state) {
        this.state = state;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }
}