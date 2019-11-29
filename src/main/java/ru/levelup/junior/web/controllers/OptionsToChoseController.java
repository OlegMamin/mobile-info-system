package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.*;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by otherz on 27.11.2019.
 */
@Controller
public class OptionsToChoseController {

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private ContractService contractService;

    @Autowired
    private OptionsRepository optionsRepository;

    @GetMapping(path = "/dashboard/options")
    public String contractsToChose(
            ModelMap model,
            @RequestParam int contractId) {
        try {
            Contract contract = contractsRepository.findById(contractId).get();
            List<Option> contractOptions = contract.getOptions();
            List<Option> notConnected = (List<Option>) optionsRepository.findAll();
            notConnected.removeAll(contractOptions);

            model.addAttribute("contractOptions", contractOptions);
            model.addAttribute("notConnected", notConnected);

            model.addAttribute("contract", contract);

            return "contractOptions";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }

    @GetMapping(path = "/dashboard/options/remove")
    public String removeOptionFromContract(
            @RequestParam int contractId,
            @RequestParam int optionId) {
        try {
            contractService.removeOption(contractId, optionId);

            return "redirect:/dashboard/options?contractId=" + contractId;
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
    @GetMapping(path = "/dashboard/options/add")
    public String contractsToChose(
            @RequestParam int contractId,
            @RequestParam int optionId) {
        try {
            contractService.connectOption(contractId, optionId);

            return "redirect:/dashboard/options?contractId=" + contractId;
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
