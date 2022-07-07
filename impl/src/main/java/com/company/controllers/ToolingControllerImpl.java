package com.company.controllers;

import com.company.API.controllers.resources.ToolingController;
import com.company.API.model.ToolingDto;
import org.springframework.ui.Model;

import java.awt.print.Pageable;

public class ToolingControllerImpl implements ToolingController {
    public String add(ToolingDto newProduct, Model model) {
        return null;
    }

    public String getAll(Model model, Pageable pageable) {
        return null;
    }

    public String getByCipher(String cipher, Model model) {
        return null;
    }

    public String delete(String cipher, Model model) {
        return null;
    }
}
