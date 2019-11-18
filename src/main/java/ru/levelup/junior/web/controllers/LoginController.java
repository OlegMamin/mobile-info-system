package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.entities.Client;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**
 * Created by otherz on 15.11.2019.
 */
@Controller
public class LoginController {
    @Autowired
    private ClientsDAO clientsDAO;

    @PostMapping(path = "/login")
    public String processLogin(
            HttpSession session,
            @RequestParam String login,
            @RequestParam String password,
            ModelMap model) {
        try {
            Client found = clientsDAO.findByLoginAndPassword(login, password);
            session.setAttribute("clientId", found.getId());
            session.setAttribute("clientName", found.getFirstName());

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
            @RequestParam("name") String firstName,
            @RequestParam("surname") String secondName,
            @RequestParam("passport-number") String passportNumber,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam("password-confirmation") String passwordConfirmation
    ) {
        if (firstName == null || firstName.length() < 2) {
            throw new IllegalArgumentException("..");
        }
        if (secondName == null || secondName.length() < 2) {
            throw new IllegalArgumentException("..");
        }
        if (passportNumber == null || passportNumber.length() < 6) {
            throw new IllegalArgumentException("..");
        }
        if (login == null || login.length() < 4) {
            throw new IllegalArgumentException("..");
        }
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("..");
        }
        if (passwordConfirmation == null || !passwordConfirmation.equals(password)) {
            throw new IllegalArgumentException("..");
        }

        long passport = Long.parseLong(passportNumber);

        try {
            clientsDAO.create(new Client(firstName, secondName, passport, login, password));
        } catch (Exception e) {
            throw new IllegalArgumentException("..");
        }
        return "redirect:/";
    }
}
