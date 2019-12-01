package ru.levelup.junior.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by otherz on 01.12.2019.
 */
public class LoginFormBean {
    @Size(min = 4, max = 32, message = "Login length should be at least 4 and at most 32 characters.")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z_0-9]*", message = "Login should consist of characters: ...")
    private String login;

    @Size(min = 4, max = 32, message = "Password length should be at least 4 and at most 32 characters.")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
