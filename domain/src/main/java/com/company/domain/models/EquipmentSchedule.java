package com.company.domain.models;

import javax.persistence.JoinColumn;

public class EquipmentSchedule {//ГРАФИК ОСНАЩЕНИЯ
    private Long id;

    @JoinColumn ("detail_id")
    private Detail detail;

    private Employee creator;

    private Product
}
