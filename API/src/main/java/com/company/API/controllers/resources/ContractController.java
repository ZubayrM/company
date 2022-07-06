package com.company.API.controllers.resources;

import com.company.API.model.ContractDto;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/contract")
public interface ContractController {

    @PostMapping("/add")
    String add(@RequestBody ContractDto dto, Model model, Errors errors);
}
