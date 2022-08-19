package com.company;

import com.company.API.model.ProductDto;
import com.company.API.responseDto.ProductDtoResponse;
import com.company.domain.models.Product;

public class ProductMapper {
    public ProductDtoResponse toDto (Product product){
        ProductDtoResponse productDtoResponse = new ProductDtoResponse();
        productDtoResponse.setName(product.getName());
        productDtoResponse.setCipher(product.getCipher());
        productDtoResponse.setRoute(product.getRoute());
        productDtoResponse.setType(product.getType());
        if (product.getMainProduct() == null){
            productDtoResponse.setProduct("");
        }
        else
        productDtoResponse.setProduct(product.getMainProduct().getCipher());
        return productDtoResponse;
    }
}
