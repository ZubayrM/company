package com.company.API.controllers.resources;

import com.company.API.model.ToolingDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RequestMapping("/api/tooling")
public interface ToolingController {

    @PostMapping("/add")
    String add(@RequestBody ToolingDto newProduct, Model model);

    @GetMapping("/all")
    String getAll(Model model, Pageable pageable);

    @GetMapping("/{cipher}")
    String getByCipher(@PathVariable String cipher, Model model);

    @DeleteMapping("/{cipher}")
    String delete(@PathVariable String cipher, Model model);

}
