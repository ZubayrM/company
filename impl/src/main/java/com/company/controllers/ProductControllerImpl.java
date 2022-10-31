package com.company.controllers;

import com.company.API.model.ProductDto;
import com.company.API.responseDto.ProductDtoResponse;
import com.company.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping ("/byCipher")
    public String getAllByCipher(@RequestParam String cipher, Model model) {
        log.info(productService.getProductListByCipher(cipher).toString());
        List<ProductDtoResponse> products = productService.getProductListByCipher(cipher);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping ("/byName")
    public String getAllByName (@RequestParam String name, Model model){
        List<ProductDtoResponse> products = productService.getProductListByName(name);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/byType")
    public String getAllByType (@RequestParam String type, Model model){
        List<ProductDtoResponse> products = productService.getProductListByType(type);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/byRoute")
    public String getAllByRoute(@RequestParam String route, Model model) {
        model.addAttribute("products", productService.getProductListByRoute(route));
        return "home";
    }

    @GetMapping("/byMP")
    public String getAllByMainProduct(@RequestParam String MP, Model model) {
        model.addAttribute("products", productService.getProductListByMainProduct(MP));
        return "home";
    }

    @GetMapping ("/")
    public String getAll(Model model) {
        List<ProductDtoResponse> products = productService.getProductList();
        model.addAttribute("products", products);
        //model.addAttribute("list", productService.getMainProductsList());
        return "home";
    }

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

    @ModelAttribute
    public void putMainProductsListForSearch(Model model){
        model.addAttribute("MPList", productService.getMainProductsList());
    }

    @GetMapping("/reset")
    public String resetFilter(){
        return "redirect:/api/product/";
    }
}
