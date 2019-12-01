package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.repositories.TariffsRepository;
import ru.levelup.junior.entities.Tariff;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by otherz on 27.11.2019.
 */
@Controller
public class TariffsToChoseController {
    @Autowired
    private TariffsRepository tariffsRepository;

    @GetMapping(path = "/dashboard/tariffs")
    public String contractsToChose(
            ModelMap model,
            @RequestParam String phoneNumber) {
        try {
            List<Tariff> tariffsToChose = (List<Tariff>) tariffsRepository.findAllOrderByPrice();
            model.addAttribute("tariffsToChose", tariffsToChose);
            model.addAttribute("phoneNumber", phoneNumber);
            return "tariffsToChose";
        } catch (NoResultException notFound) {
            return "mainPage";
        }
    }
}
