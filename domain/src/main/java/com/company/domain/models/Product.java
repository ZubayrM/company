package com.company.domain.models;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
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
    @ToString.Exclude
    private Product mainProduct;

    //@JoinColumn (name = "images")
    //private String images;

    @OneToMany
    private List<Image> images = new ArrayList<>();

    @OneToMany (mappedBy = "mainProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> children = new ArrayList<>();

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
