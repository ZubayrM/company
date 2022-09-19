package com.company.API.controllers.resources;

import com.company.API.responseDto.RegistrationForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping ("/api/registration")
public interface RegistrationController {

    @GetMapping
    String registerForm();

    @PostMapping
    String processRegistration(RegistrationForm form);
}
