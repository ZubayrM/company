package com.company.API.controllers.resources;

import com.company.API.model.EmployeeDto;
import com.company.API.responseDto.AuthUserDto;
import com.company.API.responseDto.RegistrationForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletResponse;

@RequestMapping ("/api/login")
public interface RegistrationController {

    @GetMapping("/")
    String loginForm();

    @GetMapping ("/registration")
    String registrationForm();

//    @PostMapping ("/")
//    String authenticate(AuthUserDto dto, ServletResponse response);

    @PostMapping ("/")
    String authenticate(String username, String password, ServletResponse response);

    @PostMapping ("/registration")
    String processRegistration(EmployeeDto employeeDto);
}
