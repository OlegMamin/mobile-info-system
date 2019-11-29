package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelup.junior.dao.ContractService;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.ContractsRepository;
import ru.levelup.junior.entities.Contract;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by otherz on 26.11.2019.
 */
@Controller
public class ContractsToChoseController {
    @Autowired
    private ContractsRepository contractsRepository;

    @GetMapping(path = "/dashboard/contracts")
    public String contractsToChose(ModelMap model) {
        try {
            List<Contract> contractsToChose = contractsRepository.findContractsToChose();
            model.addAttribute("contractsToChose", contractsToChose);
            return "contractsToChose";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
