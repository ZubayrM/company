package com.company.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentSchedule {//ГРАФИК ОСНАЩЕНИЯ

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn (name = "constructor")
    private Employee creator;//ИСПОЛНИТЕЛЬ-КОНСТРУКТОР

    @JoinColumn (name = "checking_engineer")
    private Employee checkingEmployee;//ПРОВЕРЯЮЩИЙ

    @JoinColumn (name = "tech_controller")
    private Employee techController;//ТЕХНОЛОГИЧЕСКИЙ КОНТРОЛЬ

    @JoinColumn (name = "time_controller")
    private Employee timeController;//НОРМОКОНТРОЛЬ

    @JoinColumn (name = "approved")
    private Employee approved;//утверждающий
}
