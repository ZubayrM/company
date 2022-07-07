package com.company.controllers;

import com.company.API.controllers.resources.DevelopmentDepartmentController;
import com.company.API.model.DevelopmentDepartmentDto;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.awt.print.Pageable;

public class DevelopmentDepartmentControllerImpl implements DevelopmentDepartmentController {
    public String add(DevelopmentDepartmentDto dto, Model model, Errors errors) {
        return null;
    }

    public String getAll(Model model, Pageable pageable) {
        return null;
    }

    public String delete(String name, Model model) {
        return null;
    }
}
