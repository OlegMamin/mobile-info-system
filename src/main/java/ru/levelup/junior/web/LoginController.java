package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
}
