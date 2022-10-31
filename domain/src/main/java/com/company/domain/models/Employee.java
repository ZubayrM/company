package com.company.domain.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "position")
    private Positions position;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    // TODO: 30.06.2022 Должности сотрудников предприятия,
    //  вовлеченные в процесс создания, согласования и утверждения оснастки
    public enum Positions {
        DESIGN_ENGINEER ("ИНЖЕНЕР-КОНСТРУКТОР"),
        HEAD_OF_DESIGN_BUREAU ("НАЧАЛЬНИК КОСТРУКТОРСКОГО БЮРО"),
        TECH_CONTROLLER ("ТЕХ.КОНТРОЛЬ"),
        TIME_CONTROLLER ("НОРМО-КОНТРОЛЬ"),
        APPROVER ("УТВЕРЖДАЮЩИЙ"),
        DEPUTY_GENERAL_DIRECTOR ("ЗАМ.ГЕН.ДИРЕКТОРА"),
        HEAD_ECONOMIST ("ГЛАВНЫЙ ЭКОНОМИСТ");

        private String position;

        Positions(String position){
            this.position = position;
        }

        public String getPosition(){
            return position;
        }
    }
}


