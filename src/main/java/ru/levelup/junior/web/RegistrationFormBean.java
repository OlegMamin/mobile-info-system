package ru.levelup.junior.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by otherz on 19.11.2019.
 */
public class RegistrationFormBean {

    @Size(min = 4, max = 32, message = "Name length should be at least 2 and at most 32 characters length.")
    @Pattern(regexp = "[a-zA-Z]*", message = "Name should consist of letters.")
    private String firstName;

    @Size(min = 4, max = 32, message = "Surname length should be at least 2 and at most 32 characters.")
    @Pattern(regexp = "[a-zA-Z]*", message = "Surname should consist of letters.")
    private String secondName;

    @Size(min = 4, max = 10, message = "Passport number length should be at least 4 and at most 10 digits.")
    @Pattern(regexp = "[0-9a-zA-Z]*", message = "Login should consist of digits.")
    private String passportNumber;

    @Size(min = 4, max = 32, message = "Login length should be at least 4 and at most 32 characters.")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z_0-9]*", message = "Login should consist of characters: ...")
    private String login;

    @Size(min = 4, max = 32, message = "Password length should be at least 4 and at most 32 characters.")
    private String password;

    private String passwordConfirmation;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
