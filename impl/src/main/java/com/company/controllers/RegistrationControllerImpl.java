package com.company.controllers;

import com.company.API.controllers.resources.RegistrationController;
import com.company.API.model.EmployeeDto;
import com.company.API.responseDto.AuthUserDto;
import com.company.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;

@Controller
@Slf4j
public class RegistrationControllerImpl implements RegistrationController {

    private EmployeeService employeeService;

    @Autowired
    public RegistrationControllerImpl(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping ("/")
    public String loginForm(){
        return "login";
    }

    @GetMapping ("/registration")
    public String registrationForm() {
        return "registration";
    }

//    @PostMapping("/")
////    public String authenticate(@ModelAttribute AuthUserDto dto, ServletResponse response){
////        log.info("11111");
////        log.info(dto.toString());
////        try{
////            employeeService.authenticate(dto, response);
////            return "redirect:/api/product/";
////        }
////        catch (AuthenticationException e){
////            log.info(e.getMessage());
////            e.printStackTrace();
////            return "redirect:/api/login/";
////        }
////    }

    @PostMapping("/")
    public String authenticate(@RequestParam String username, @RequestParam String password, ServletResponse response){
        log.info("мы должны сюда попасть");
        try{
            employeeService.authenticate(new AuthUserDto(username, password), response);
            return "redirect:/api/product/";
        }
        catch (AuthenticationException e){
            log.info(e.getMessage());
            e.printStackTrace();
            return "redirect:/api/login/";
        }
    }

    @PostMapping ("/registration")
    public String processRegistration (EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
        return "redirect:/api/product/";
    }
}
