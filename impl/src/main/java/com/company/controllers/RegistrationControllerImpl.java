package com.company.controllers;

import com.company.API.controllers.resources.RegistrationController;
import com.company.API.model.EmployeeDto;
import com.company.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    private EmployeeService employeeService;

    @Autowired
    public RegistrationControllerImpl(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public String registerForm(){
        return "login";
    }

    @PostMapping
    public String processRegistration (EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
        return "redirect:/api/product/";
    }
}
