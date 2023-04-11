package com.company.controllers;

import com.company.API.model.ProductDto;
import com.company.API.responseDto.ProductDtoResponse;
import com.company.domain.models.Image;
import com.company.repositories.ProductRepository;
import com.company.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class ProductControllerImpl implements com.company.API.controllers.resources.ProductController {

    private final ProductService productService;
    private final ProductRepository repository;

    @Autowired
    public ProductControllerImpl(ProductService productService, ProductRepository repository) {
        this.productService = productService;
        this.repository = repository;
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

    @PostMapping("/image/{cipher}")
    public String addImage(@RequestAttribute MultipartFile file, @PathVariable String cipher, Model model) throws IOException {
        if (file != null){
            //ProductDtoResponse product = productService.getProduct(cipher);
            productService.putImage(cipher, file);
            //model.addAttribute("selectedProd", product);
        }
        return "redirect:/api/product/{cipher}";
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

    @GetMapping("/bySearch")
    public String getProductListBySearch(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "cipher", required = false) String cipher,
                                 @RequestParam(value = "type", required = false) String type, @RequestParam(value = "route", required = false) String route,
                                 @RequestParam(value = "mp", required = false) String mp, Model model){
        model.addAttribute("products", productService.getProductListBySearch(name, cipher, type, route, mp));
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
        log.info("Мы получили фото " + product.getImages());
        List<Image> list = product.getImages();
        model.addAttribute("selectedProduct", product);
        model.addAttribute("images", product.getImages());
        return "detail";
    }

    @DeleteMapping ("/image/{imageId}")
    public String deleteImage (@PathVariable Long imageId, Model model){
        productService.deleteImage(imageId);
        return "redirect:/api/product/{cipher}";
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
