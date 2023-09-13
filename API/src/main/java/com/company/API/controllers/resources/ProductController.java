package com.company.API.controllers.resources;

import com.company.API.model.ProductDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RequestMapping("/api/product")
public interface ProductController {

    @PostMapping("/add")
    String add(@RequestBody ProductDto newProduct, Model model, Errors errors);

    @PostMapping("/")
    String addList(@RequestAttribute MultipartFile file,Model model);

    @GetMapping("/{page}")
    String pagination(Model model, @PathVariable int page);

    @GetMapping("/{name}")
    String getAllByName(@PathVariable String name, Model model);

    @GetMapping("/{cipher}")
    String getAllByCipher(@PathVariable String cipher, Model model);

    @GetMapping("/{cipher}")
    String getByCipher(@PathVariable String cipher, Model model);

    @GetMapping("/{name}{cipher}{route}{type}{mp}")
    String getProductListBySearch(@PathVariable String name, @PathVariable String cipher, @PathVariable String route, @PathVariable String type, @PathVariable String mp, Model model);

    @DeleteMapping("/{cipher}")
    String delete(@PathVariable String cipher, Model model);

    @DeleteMapping("/")
    String deleteAll();
}
