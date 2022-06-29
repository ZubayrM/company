package com.company.domain.models;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tooling {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cipher;

    private String purpose;//назначение

    @JoinColumn ("detail_id")
    private Detail detail;

    private Employee creator;//ИСПОЛНИТЕЛЬ-КОНСТРУКТОР

    private Employee checkingEmployee;//ПРОВЕРЯЮЩИЙ

    private Employee techController;//ТЕХНОЛОГИЧЕСКИЙ КОНТРОЛЬ

    private Employee timeController;//НОРМОКОНТРОЛЬ

    private Employee approver;//утверждающий
}
