package com.company.controllers;

import com.company.API.controllers.HomeResource;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController implements HomeResource {

    public String home() {
        return "home";
    }
}
