package ru.levelup.junior.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by otherz on 16.11.2019.
 */
@Controller
public class MainPageController {
    @GetMapping(path = "/")
    public String index() {
        return "mainPage";
    }
}
