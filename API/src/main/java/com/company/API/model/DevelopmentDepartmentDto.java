package com.company.API.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DevelopmentDepartmentDto {

    @NotBlank(message = "поле не заполнено")
    private String name;
}
