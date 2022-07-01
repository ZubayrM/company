package com.company.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tooling {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cipher", unique = true)
    private String cipher;

    @JoinColumn (name="product_id")
    private Product product;

    private Employee creator;//ИСПОЛНИТЕЛЬ-КОНСТРУКТОР

    private Employee checkingEmployee;//ПРОВЕРЯЮЩИЙ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_checking")
    private Status statusChecking;

    private Employee techController;//ТЕХНОЛОГИЧЕСКИЙ КОНТРОЛЬ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_tech_controller")
    private Status statusTechController;

    private Employee timeController;//НОРМОКОНТРОЛЬ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_time_controller")
    private Status statusTimeController;

    private Employee approver;//утверждающий

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_approver")
    private Status statusApprover;

    public enum Status{
        UNCHECKED ,
        CHEKED
    }
}
