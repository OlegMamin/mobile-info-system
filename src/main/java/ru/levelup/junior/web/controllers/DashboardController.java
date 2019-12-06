package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.services.DashboardService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * Created by otherz on 15.11.2019.
 */
@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping(path = "/dashboard")
    public String dashboard(Principal principal, ModelMap model, HttpSession session) {
        try {
            List<Contract> contracts = dashboardService.getContractsByLogin(principal.getName());
            Client found =  clientsRepository.findByLogin(principal.getName());

            session.setAttribute("clientId", found.getId());
            session.setAttribute("clientName", found.getFirstName() + " " + found.getLastName());

            model.addAttribute("contracts", contracts);

            if (found.isAdmin()) {
                session.setAttribute("isAdmin", found.isAdmin());
                return "redirect: /admin";
            }
            return "dashboard";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
