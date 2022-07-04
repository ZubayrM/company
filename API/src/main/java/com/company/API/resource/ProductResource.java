package com.company.API.resource;

import com.company.API.model.ProductDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/product")
public interface ProductResource {

    @PostMapping("/add")
    String add(@RequestBody ProductDto newProduct, Model model);

    @PostMapping("/addAll")
    String addList(@RequestAttribute MultipartFile file,Model model);

    @GetMapping("/all")
    String getAll(Model model);

    @GetMapping("/{cipher}")
    String getProduct(Model model);

    @DeleteMapping("/{cipher}")
    void delete(@PathVariable String cipher, Model model);

}
