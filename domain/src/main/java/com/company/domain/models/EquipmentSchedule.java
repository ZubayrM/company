package com.company.domain.models;

import javax.persistence.*;

public class EquipmentSchedule {//ГРАФИК ОСНАЩЕНИЯ

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn (name = "constructor")
    private Employee creator;//ИСПОЛНИТЕЛЬ-КОНСТРУКТОР

    @JoinColumn
    private Employee checkingEmployee;//ПРОВЕРЯЮЩИЙ

    @JoinColumn
    private Employee techController;//ТЕХНОЛОГИЧЕСКИЙ КОНТРОЛЬ

    @JoinColumn
    private Employee timeController;//НОРМОКОНТРОЛЬ

    @JoinColumn
    private Employee approver;//утверждающий
}
