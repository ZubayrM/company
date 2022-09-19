package com.company.API.model;

import com.company.domain.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank (message = "Поле не заполнено")
    private String name;
    @NotBlank (message = "Поле не заполнено")
    private String surname;
    @NotBlank (message = "Поле не заполнено")
    private String username;
    @NotBlank (message = "Поле не заполнено")
    private String password;
    @NotBlank (message = "Поле не заполнено")
    private String position;
}
