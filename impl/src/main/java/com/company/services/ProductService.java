package com.company.services;

import com.company.API.model.ProductDto;
import com.company.ExcelParser;
import com.company.domain.models.Product;
import com.company.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct (ProductDto dto){
        Product product1 = null;
        Product product = null;
        Optional<Product> oProduct1 = productRepository.findByCipher(dto.getCipher());
        if (!oProduct1.isPresent()){
        //if (productRepository.findByCipher(dto.getCipher()) == null){
            if (dto.getProduct() != null){
                Optional<Product> oProduct = productRepository.findByCipher(dto.getProduct().getCipher());
                if (!oProduct.isPresent()){
                    product = productRepository.save(Product.builder()
                            .name(dto.getProduct().getName())
                            .cipher(dto.getProduct().getCipher())
                            .route(dto.getProduct().getRoute())
                            .type(dto.getProduct().getType())
                            .build());
                }
            }
            product1 = productRepository
                    .save( Product.builder()
                            .name(dto.getName())
                            .cipher(dto.getCipher())
                            .route(dto.getRoute())
                            .type(dto.getType())
                            .mainProduct(product)
                            .build());
        }
        return product1.getCipher();
    }

    public void addProductList (MultipartFile multipartFile){
        List<Product> list = ExcelParser.excelParsing(multipartFile);
        for (Product product : list){
            Optional<Product> oProduct1 = productRepository.findByCipher(product.getCipher());
            if (!oProduct1.isPresent()){
                productRepository.save(product);
            }
        }
    }

    public String getProduct (String cipher){
        Product product = productRepository.findByCipher(cipher).get();
        return product.getCipher();
    }

    public List<Product> getProductList (){
        return productRepository.findAll();
    }

    public void deleteProduct (String cipher){
        Product product = productRepository.findByCipher(cipher).get();
        productRepository.delete(product);
    }
}
