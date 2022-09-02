package com.company.controllers;

import com.company.API.model.ProductDto;
import com.company.API.responseDto.ProductDtoResponse;
import com.company.domain.models.Product;
import com.company.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ProductControllerImpl implements com.company.API.controllers.resources.ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    public String add(@Valid ProductDto newProduct, Model model, Errors errors) {
        log.info("method add: " + newProduct.toString());
        productService.addProduct(newProduct);
        return "home";
    }

    @PostMapping ("/")
    public String addList(@RequestAttribute MultipartFile file, Model model) {
        if (file != null){
            productService.addProductList(file);
        }
        return "redirect:/api/product/";
    }

    @GetMapping ("/getList/{cipher}")
    public String getAllByCipher(@PathVariable String cipher, Model model) {
        log.info(productService.getProductListByCipher(cipher).toString());
        List<ProductDtoResponse> products = productService.getProductListByCipher(cipher);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping ("/")
    public String getAll(Model model) {
        List<ProductDtoResponse> products = productService.getProductList();
        model.addAttribute("products", products);
        return "home";
    }

//    public String getByCipher(String cipher, Model model) {
//        return null;
//    }

    //@Transactional
    @DeleteMapping("/{cipher}")
    public String delete(@PathVariable String cipher, Model model) {
        productService.deleteProduct(cipher);
        return "redirect:/api/product/";
    }

    @GetMapping ("/{cipher}")//тестирую метод по получению продукта
    public String getByCipher(@PathVariable String cipher, Model model) {
        ProductDtoResponse product = productService.getProduct(cipher);
        model.addAttribute("selectedProduct", product);
        return "detail";
    }

    @PostMapping("/add2")
    public String add(@Valid @RequestBody ProductDto newProduct, Errors errors) {
        if (errors.hasErrors()){
            return "redirect:/api/product/";
        }
        log.info("method add: " + newProduct.toString());
        productService.addProduct(newProduct);
        return "redirect:/api/product/";
    }

    @DeleteMapping ("/")
    public String deleteAll(){
        productService.deleteAll();
        return "redirect:/api/product/";
    }
}
