package com.company.API.responseDto;

import com.company.domain.models.Image;
import com.company.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {
    @NotBlank(message = "поле не заполнено")
    private String name;

    @NotBlank(message = "поле не заполнено")
    private String cipher;

    @NotBlank(message = "поле не заполнено")
    private String route;

    @NotBlank(message = "поле не заполнено")
    private Product.Type type;

    @NotBlank(message = "поле не заполнено")
    private String product;

    @NotBlank (message = "поле не заполнено")
    private List<Image> images;
}
