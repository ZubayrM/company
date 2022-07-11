package com.company.services;

import com.company.API.model.ProductDto;
import com.company.ExcelParser;
import com.company.domain.models.Product;
import com.company.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

//    @Autowired
//    public void setProductRepository(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public void addProduct (ProductDto dto){
        if (productRepository.findByCipher(dto.getCipher()) == null){
            Product product = productRepository.findByCipher(dto.getProduct().getCipher());
            if (product == null){
                product = productRepository.save(Product.builder()
                        .name(dto.getProduct().getName())
                        .cipher(dto.getProduct().getCipher())
                        .build());
            }
            productRepository
                    .save( Product.builder()
                            .name(dto.getName())
                            .cipher(dto.getCipher())
                            .mainProduct(product)
                            .build());
        }
    }

    public void addProductList (MultipartFile multipartFile){
        ExcelParser.excelParsing(multipartFile);
    }

    public Product getProduct (String cipher){
        return productRepository.getProductByCipher(cipher);
    }

    public List<Product> getProductList (){
        return productRepository.findAll();
    }

    public void deleteProduct (String cipher){
        productRepository.deleteProductByCipher(cipher);
    }
}
