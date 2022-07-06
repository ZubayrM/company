package com.company.API.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContractDto {

    @NotBlank(message = "поле не заполнено")
    private String task; //предмет договора

    @NotBlank(message = "отдел не выбран")
    private String customer;// заказчик

    @NotBlank(message = "отдел не выбран")
    private String executor; //исполнитель
}
