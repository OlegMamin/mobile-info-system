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
import ru.levelup.junior.dao.ClientsRepository;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.web.RegistrationFormBean;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**
 * Created by otherz on 15.11.2019.
 */
@Controller
public class LoginController {
    @Autowired
    private ClientsRepository clientsRepository;

    @PostMapping(path = "/login")
    public String processLogin(
            HttpSession session,
            @RequestParam String login,
            @RequestParam String password,
            ModelMap model) {
        try {
            Client found = clientsRepository.findByLoginAndPassword(login, password);
            session.setAttribute("clientId", found.getId());
            session.setAttribute("clientName", found.getFirstName());
            session.setAttribute("isAdmin", found.isAdmin());

            return "redirect:/dashboard";
        } catch (NoResultException notFound) {
            model.addAttribute("login", "login");
            return "mainPage";
        }
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

        if (result.hasErrors()) {
            return "registration";
        }

        try {
            //save() do not throw exception!!
            clientsRepository.save(new Client(
                    form.getFirstName(), form.getSecondName(), form.getPassportNumber(), form.getLogin(), form.getPassword()));
        } catch (Exception e) {
            result.addError(new FieldError("form", "login",
                    "Client with this login is already registered."));
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
