package com.company.API.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "поле не заполнено")
    private String name;

    @NotBlank(message = "поле не заполнено")
    private String cipher;

    private ProductDto product;
}
