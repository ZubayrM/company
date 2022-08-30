package com.company.domain.models;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "cipher")
    private String cipher;

    @Column (name = "route")
    private String route;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "type")
    private Type type;

    @JoinColumn (name = "product")
    @ManyToOne
    private Product mainProduct;

    @OneToMany (mappedBy = "mainProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> childs = new ArrayList<>();

    public enum Type{
        DETAIL ("Деталь"),
        ASSEMBLY ("Сборочная единица"),
        PURCHASED ("Покупная"),
        MATERIAL ("Материал"),
        NORMALIZED ("Стандарное изделие"),
        ASSEMBLY_NORMAL ("Сборочная нормаль");

        private String type;

        Type(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }
    }
}
