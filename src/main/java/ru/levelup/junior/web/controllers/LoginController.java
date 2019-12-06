package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.web.LoginFormBean;
import ru.levelup.junior.web.RegistrationFormBean;
import javax.servlet.http.HttpSession;

/**
 * Created by otherz on 15.11.2019.
 */
@Controller
public class LoginController {
    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private PasswordEncoder encoder;

//    @PostMapping(path = "/login") //we have Spring SecurityForm
    public String newLogin(
            HttpSession session,
            @Validated
            @ModelAttribute("loginForm") LoginFormBean loginForm,
            BindingResult result
    ) {
//        Client found = clientsRepository.findByLoginAndEncryptedPassword(loginForm.getLogin(), encoder.encode(loginForm.getPassword()));
        Client found = clientsRepository.findByLogin(loginForm.getLogin());
        if (found == null || !encoder.matches(loginForm.getPassword(), found.getEncryptedPassword())) {
            result.addError(new FieldError("loginForm", "login", loginForm.getLogin(),
                    false, null, null,
                    "Incorrect login or password"));
        }

        if (result.hasErrors()) {
            return "mainPage";
        }
        session.setAttribute("clientId", found.getId());
        session.setAttribute("clientName", found.getFirstName() + " " + found.getLastName());
        session.setAttribute("isAdmin", found.isAdmin());

        if (found.isAdmin()) {
            return "redirect: /admin";
        }
        return "redirect: /dashboard";
    }

    @GetMapping(path = "/register")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(path = "/register")
    public String registrationForm(
            @Validated
            @ModelAttribute("form") RegistrationFormBean form,
            BindingResult result
    ) {
        if (form.getPasswordConfirmation() == null || !form.getPasswordConfirmation().equals(form.getPassword())) {
            result.addError(new FieldError("form", "passwordConfirmation",
                    "Confirmation doesn't match."));
        }

        Client foundByPassport = clientsRepository.findByPassportNumber(form.getPassportNumber());
        if (foundByPassport != null){
            result.addError(new FieldError("form", "passportNumber", form.getPassportNumber(),
                    false, null, null,
                    "Client with this passport number is already registered."));
        }

        try {
            clientsRepository.save(new Client(form.getFirstName(),
                    form.getSecondName(), form.getPassportNumber(),
                    form.getLogin(), encoder.encode(form.getPassword())));
        } catch (Exception e) {
            result.addError(new FieldError("form", "login",
                    "User with this login is already registered"));
        }

        if (result.hasErrors()) {
            return "registration";
        }

        return "redirect:/";
    }

    @ModelAttribute("form")
    public RegistrationFormBean newFormBean() {
        RegistrationFormBean bean = new RegistrationFormBean();
        bean.setFirstName("");
        bean.setSecondName("");
        bean.setPassportNumber("");
        bean.setLogin("");
        bean.setPassword("");
        bean.setPasswordConfirmation("");
        return bean;
    }
}
