package com.company.resource;

import com.company.API.resource.HomeResource;
import org.springframework.stereotype.Controller;

@Controller
public class HomeResourceImpl implements HomeResource {

    public String home() {
        return "home";
    }
}
