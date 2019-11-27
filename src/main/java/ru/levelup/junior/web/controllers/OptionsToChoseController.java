package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by otherz on 27.11.2019.
 */
@Controller
public class OptionsToChoseController {

    @Autowired
    private ContractsDAO contractsDAO;

    @GetMapping(path = "/dashboard/options")
    public String contractsToChose(
            ModelMap model,
            @RequestParam int contractId) {
        try {
            Contract contract = contractsDAO.findById(contractId);
            List<Option> options = contract.getOptions();

            model.addAttribute("optionList", options);
            model.addAttribute("contract", contract);

            return "contractOptions";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
