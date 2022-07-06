package com.company.API.controllers.resources;

import com.company.API.model.ProductDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RequestMapping("/api/product")
public interface ProductController {

    @PostMapping("/add")
    String add(@RequestBody ProductDto newProduct, Model model);

    @PostMapping("/addAll")
    String addList(@RequestAttribute MultipartFile file,Model model);

    @GetMapping("/all")
    String getAll(Model model, Pageable pageable);

    @GetMapping("/{cipher}")
    String getByCipher(@PathVariable String cipher, Model model);

    @DeleteMapping("/{cipher}")
    String delete(@PathVariable String cipher, Model model);
}
