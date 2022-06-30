package com.company.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    @Setter
    private String employeeName;

    private Positions position;

    // TODO: 30.06.2022 Должности сотрудников предприятия,
    //  вовлеченные в процесс создания, согласования и утверждения оснастки
    public enum Positions {
        DESIGN_ENGINEER,//ИНЖЕНЕР-КОНСТРУКТОР
        HEAD_OF_DESIGN_BUREAU,//ПРОВЕРЯЮЩИЙ, НАЧАЛЬНИК КОСТРУКТОРСКОГО БЮРО
        TECH_CONTROLLER,//ТЕХ.КОНТРОЛЬ
        TIME_CONTROLLER,//НОРМО-КОНТРОЛЬ
        APPROVER,//УТВЕРЖДАЮЩИЙ
        DEPUTY_GENERAL_DIRECTOR,//ЗАМ.ГЕН.ДИРЕКТОРА
        HEAD_ECONOMIST//ГЛАВНЫЙ ЭКОНОМИСТ
    }
}


