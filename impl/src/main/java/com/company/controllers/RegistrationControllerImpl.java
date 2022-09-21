package com.company.controllers;

import com.company.API.controllers.resources.RegistrationController;
import com.company.API.responseDto.RegistrationForm;
import com.company.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationControllerImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping ("/")
    public String registerForm(){
        return "registration";
    }

    @PostMapping ("/")
    public String processRegistration (RegistrationForm form){
        employeeRepository.save(form.toEmployee(passwordEncoder));
        return "redirect:/api/product/";
    }
}
