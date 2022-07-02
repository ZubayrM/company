package com.company.API.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
public interface HomeResource {

    @GetMapping
    String home();
}
