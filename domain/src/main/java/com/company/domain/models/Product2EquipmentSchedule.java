package com.company.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product2EquipmentSchedule {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id")
    private Product product;

    @JoinColumn(name = "equipment_schedule_id")
    private EquipmentSchedule equipmentSchedule;
}
