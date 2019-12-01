package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.web.LoginFormBean;

import javax.servlet.http.HttpSession;

/**
 * Created by otherz on 01.12.2019.
 */
@Controller
public class NewLoginController {

    @Autowired
    private ClientsRepository clientsRepository;

    @ModelAttribute("loginForm")
    public LoginFormBean newLoginFormBean() {
        LoginFormBean formBean = new LoginFormBean();
        formBean.setLogin("");
        formBean.setPassword("");
        return formBean;
    }
    @PostMapping(path = "/newLogin")
    public String newLogin(
            HttpSession session,
            @Validated
            @ModelAttribute("loginForm") LoginFormBean loginForm,
            BindingResult result
    ) {
        Client found = clientsRepository.findByLoginAndPassword(loginForm.getLogin(), loginForm.getPassword());

        if (found == null) {
            result.addError(new FieldError("loginForm", "login",
                    "Incorrect login or password"));
        }

        if (result.hasErrors()) {
            return "newLogin";
        }
        session.setAttribute("clientId", found.getId());
        session.setAttribute("clientName", found.getFirstName());
        session.setAttribute("isAdmin", found.isAdmin());

        return "redirect:/dashboard";
    }
    @GetMapping(path = "/newLogin")
    public String newLoginPage() {
        return "newLogin";
    }
}
