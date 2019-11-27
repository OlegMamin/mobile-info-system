package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by otherz on 27.11.2019.
 */
@Controller
public class TariffsToChoseController {
    @Autowired
    private TariffsDAO tariffsDAO;

    @GetMapping(path = "/dashboard/tariffs")
    public String contractsToChose(
            ModelMap model,
            @RequestParam String phoneNumber) {
        try {
            List<Tariff> tariffsToChose = tariffsDAO.findAllTariffs();
            model.addAttribute("tariffsToChose", tariffsToChose);
            model.addAttribute("phoneNumber", phoneNumber);
            return "tariffsToChose";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
