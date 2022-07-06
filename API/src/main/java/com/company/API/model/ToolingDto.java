package com.company.API.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ToolingDto {

    @NotBlank(message = "поле не заполнено")
    private String name;

    @NotBlank(message = "поле не заполнено")
    private String cipher;

    private String product;

}
