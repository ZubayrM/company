package com.company.API.controllers.resources;

import com.company.API.model.EmployeeDto;
import com.company.API.responseDto.RegistrationForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping ("/api/login")
public interface RegistrationController {

    @GetMapping
    String loginForm();

    @GetMapping ("/registration")
    String registrationForm();

    @PostMapping ("/authenticate")
    String authenticate(EmployeeDto dto);

    @PostMapping ("/registration")
    String processRegistration(EmployeeDto employeeDto);
}
