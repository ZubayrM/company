package com.company.resource;

import com.company.API.model.ProductDto;
import com.company.API.resource.ProductResource;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductResourceImpl implements ProductResource {

    private final ProductRepository productRepository;

    @Autowired
    public ProductResourceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String add(ProductDto newProduct, Model model) {
        return null;
    }

    public String addList(MultipartFile file, Model model) {
        return null;
    }

    public String getAll(Model model) {
        return null;
    }

    public void delete(String cipher, Model model) {
        productRepository.deleteByCipher(cipher);
    }
}
