package ru.levelup.junior.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.levelup.junior.web.LoginFormBean;

/**
 * Created by otherz on 16.11.2019.
 */
@Controller
public class MainPageController {
    @ModelAttribute("loginForm")
    public LoginFormBean newLoginFormBean() {
        LoginFormBean formBean = new LoginFormBean();
        formBean.setLogin("");
        formBean.setPassword("");
        return formBean;
    }
    @GetMapping(path = "/")
    public String index() {
        return "mainPage";
    }
}
