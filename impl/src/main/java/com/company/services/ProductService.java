package com.company.services;

import com.company.API.model.ProductDto;
import com.company.ExcelParser;
import com.company.domain.models.Product;
import com.company.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    public void addProductList (MultipartFile multipartFile){//для теста
        List<Product> list = ExcelParser.excelParsing(multipartFile);
        for (Product product : list){
            Optional<Product> oProduct1 = productRepository.findByCipher(product.getCipher());
            if (!oProduct1.isPresent()){
                productRepository.save(product);
            }
        }
    }

    public Product getProduct (String cipher){
        Product product = productRepository.findByCipher(cipher).get();
        return product;
    }

    public List<Product> getProductListByCipher (String value){
        List<Product> list = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            if (product.getCipher().contains(value)){
                list.add(product);
            }
        }
        return list;
    }

    public List<Product> getProductListByName (String value){
        List<Product> list = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            if (product.getName().contains(value)){
                list.add(product);
            }
        }
        return list;
    }

    public List<Product> getProductListByRoute (String value){
        List<Product> list = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            if (product.getRoute().contains(value)){
                list.add(product);
            }
        }
        return list;
    }

    public List<Product> getProductListByType (String value){
        List<Product> list = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            if (product.getType().equals(value)){
                list.add(product);
            }
        }
        return list;
    }

    public List<Product> getProductListByMainProduct (String value){
        List<Product> list = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            if (product.getMainProduct().getCipher().contains(value)){
                list.add(product);
            }
        }
        return list;
    }

    public void deleteProduct (String cipher){
        Product product = productRepository.findByCipher(cipher).get();
        productRepository.delete(product);
    }

    public void deleteAll (){
        productRepository.deleteAll();
    }
}
