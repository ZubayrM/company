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

    @JoinColumn (name = "creator")
    private Employee creator;//ИСПОЛНИТЕЛЬ-КОНСТРУКТОР

    @JoinColumn (name = "checking_employee")
    private Employee checkingEmployee;//ПРОВЕРЯЮЩИЙ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_checking")
    private Status statusChecking;

    @JoinColumn (name = "tech_controller")
    private Employee techController;//ТЕХНОЛОГИЧЕСКИЙ КОНТРОЛЬ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_tech_controller")
    private Status statusTechController;

    @JoinColumn (name = "time_controller")
    private Employee timeController;//НОРМОКОНТРОЛЬ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_time_controller")
    private Status statusTimeController;

    @JoinColumn (name = "approved")
    private Employee approved;//утверждающий

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_approved")
    private Status statusApprover;

    public enum Status{
        UNCHECKED,
        CHECKED
    }
}
