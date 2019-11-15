package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelup.junior.entities.Contract;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by otherz on 15.11.2019.
 */
@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(params = "/dashboard")
    public String dashboard(HttpSession session, ModelMap model) {
        try {
            int clientId = (int) session.getAttribute("clientId");
            List<Contract> contracts = dashboardService.getContracts(clientId);

            model.addAttribute("contracts", contracts);

            return "dashboard";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
