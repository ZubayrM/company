package com.company.API.controllers.resources;

import com.company.API.model.DevelopmentDepartmentDto;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RequestMapping("/api/developmentDepartment")
public interface DevelopmentDepartmentController {

    @PostMapping("/add")
    String add(@RequestBody DevelopmentDepartmentDto dto, Model model, Errors errors);

    @GetMapping("/all")
    String getAll(Model model, Pageable pageable);

    @DeleteMapping("/{name}")
    String delete(@PathVariable String name, Model model);

}
