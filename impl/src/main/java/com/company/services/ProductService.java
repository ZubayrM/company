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

    public void addProduct (ProductDto dto){
        if (productRepository.findByCipher(dto.getCipher()) == null){
            Optional<Product> oProduct = productRepository.findByCipher(dto.getProduct().getCipher());
            Product product = null;
            if (!oProduct.isPresent()){
                product = oProduct.get();
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
        for (Product product : ExcelParser.excelParsing(multipartFile)){
            if (productRepository.findByCipher(product.getCipher()) == null){
                productRepository.save(product);
            }
        }
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
