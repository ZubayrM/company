package com.company.controllers;

import com.company.API.controllers.resources.RegistrationController;
import com.company.API.model.EmployeeDto;
import com.company.API.responseDto.RegistrationForm;
import com.company.repositories.EmployeeRepository;
import com.company.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    private EmployeeService employeeService;
    private PasswordEncoder encoder;

    @Autowired
    public RegistrationControllerImpl(EmployeeService employeeService, PasswordEncoder encoder){
        this.employeeService = employeeService;
        this.encoder = encoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration (EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto, encoder);
        return "redirect:/api/product/";
    }
}
