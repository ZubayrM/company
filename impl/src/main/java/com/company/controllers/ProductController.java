package com.company.controllers;

import com.company.API.model.ProductDto;
import com.company.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@Controller
public class ProductController implements com.company.API.controllers.resources.ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public String add(ProductDto newProduct, Model model) {
        return null;
    }

    public String addList(MultipartFile file, Model model) {
        return null;
    }

    public List<ProductDto> getAll(Model model, Pageable pageable) {
        return null;
    }

    public ProductDto getProduct(Model model) {
        return null;
    }

    public void delete(String cipher, Model model) {
    }
}
