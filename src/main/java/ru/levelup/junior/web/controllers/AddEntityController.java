package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.ContractsDAO;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**
 * Created by otherz on 26.11.2019.
 */
@Controller
public class AddEntityController {

    @Autowired
    private ContractsDAO contractsDAO;

    @GetMapping(path = "/addContract")
    public String setClientToContract(HttpSession session, ModelMap model,
                              @RequestParam String phoneNumber) {
        try {
            int clientId = (int) session.getAttribute("clientId");
            contractsDAO.setClientToContract(phoneNumber, clientId);

            return "redirect: /dashboard";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
