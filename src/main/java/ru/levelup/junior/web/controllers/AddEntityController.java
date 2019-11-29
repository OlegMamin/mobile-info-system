package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.ContractService;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**
 * Created by otherz on 26.11.2019.
 */
@Controller
public class AddEntityController {

    @Autowired
    private ContractService contractService;

    @GetMapping(path = "/addContract")
    public String setClientToContract(
            HttpSession session,
            @RequestParam String phoneNumber,
            @RequestParam int tariffId) {
        try {
            int clientId = (int)session.getAttribute("clientId");
            contractService.setClientToContract(phoneNumber, clientId);
            contractService.setTariffToContract(phoneNumber, tariffId);

            return "redirect: /dashboard";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
