package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.services.ContractService;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by otherz on 26.11.2019.
 */
@Controller
public class AddEntityController {

    @Autowired
    private ContractService contractService;

    @GetMapping(path = "dashboard/addContract")
    public String setClientToContract(
            Principal principal,
            @RequestParam String phoneNumber,
            @RequestParam int tariffId) {
        try {
            contractService.setClientToContract(phoneNumber, principal.getName());
            contractService.setTariffToContract(phoneNumber, tariffId);

            return "redirect:/dashboard";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
