package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(path = "/login")
    public String newLogin(
            HttpSession session,
            @Validated
            @ModelAttribute("loginForm") LoginFormBean loginForm,
            BindingResult result
    ) {
        Client found = clientsRepository.findByLoginAndPassword(loginForm.getLogin(), loginForm.getPassword());

        if (found == null) {
            result.addError(new FieldError("loginForm", "login", loginForm.getLogin(),
                    false, null, null,
                    "Incorrect login or password"));
        }

        if (result.hasErrors()) {
            return "mainPage";
        }
        session.setAttribute("clientId", found.getId());
        session.setAttribute("clientName", found.getFirstName());
        session.setAttribute("isAdmin", found.isAdmin());

        return "redirect:/dashboard";
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

        Client foundByLogin = clientsRepository.findByLogin(form.getLogin());
        if (foundByLogin != null){
            result.addError(new FieldError("form", "login", form.getLogin(),
                    false, null, null,
                    "Client with this login is already registered."));
        }

        Client foundByPassport = clientsRepository.findByPassportNumber(form.getPassportNumber());
        if (foundByPassport != null){
            result.addError(new FieldError("form", "passportNumber", form.getPassportNumber(),
                    false, null, null,
                    "Client with this passport number is already registered."));
        }

        if (result.hasErrors()) {
            return "registration";
        }

        Client tryingToSave = new Client(
                form.getFirstName(), form.getSecondName(),
                form.getPassportNumber(), form.getLogin(), form.getPassword());
        clientsRepository.save(tryingToSave);

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
