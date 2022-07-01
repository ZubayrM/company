package com.company.domain.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "position")
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


