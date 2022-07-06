package com.company.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController implements com.company.API.controllers.HomeController {

    public String home() {
        return "home";
    }
}
