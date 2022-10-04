package com.company.controllers;

import com.company.API.controllers.resources.RegistrationController;
import com.company.API.model.EmployeeDto;
import com.company.API.responseDto.AuthUserDto;
import com.company.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.ServletResponse;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    private EmployeeService employeeService;

    @Autowired
    public RegistrationControllerImpl(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public String loginForm(){
        return "login";
    }

    @Override
    public String registrationForm() {
        return "registration";
    }

    public String authenticate(@RequestAttribute AuthUserDto dto, ServletResponse response){
        try{
            employeeService.authenticate(dto, response);
            return "redirect:/api/product/";
        }
        catch (AuthenticationException e){
            e.printStackTrace();
            return "/authenticate";
        }
    }

    public String processRegistration (EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
        return "redirect:/api/product/";
    }
}
