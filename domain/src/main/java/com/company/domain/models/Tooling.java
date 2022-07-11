package com.company.domain.models;

import com.company.domain.models.enums.Signature;
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
    @ManyToOne
    private Product product;

    @JoinColumn (name = "creator")
    @ManyToOne
    private Employee creator;//ИСПОЛНИТЕЛЬ-КОНСТРУКТОР

    @JoinColumn (name = "checking_employee")
    @ManyToOne
    private Employee checkingEmployee;//ПРОВЕРЯЮЩИЙ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_checking")
    private Signature statusChecking;

    @JoinColumn (name = "tech_controller")
    @ManyToOne
    private Employee techController;//ТЕХНОЛОГИЧЕСКИЙ КОНТРОЛЬ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_tech_controller")
    private Signature statusTechController;

    @JoinColumn (name = "time_controller")
    @ManyToOne
    private Employee timeController;//НОРМОКОНТРОЛЬ

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_time_controller")
    private Signature statusTimeController;

    @JoinColumn (name = "approved")
    @ManyToOne
    private Employee approved;//утверждающий

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_approved")
    private Signature statusApprover;
}
