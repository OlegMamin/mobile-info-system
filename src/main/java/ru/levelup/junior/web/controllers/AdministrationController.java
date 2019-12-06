package ru.levelup.junior.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by otherz on 05.12.2019.
 */
@Controller
public class AdministrationController {
    @GetMapping(path = "/admin")
    public String adminPage(){
        return "adminPage";
    }
}
