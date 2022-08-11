package com.company.domain.models;

import lombok.*;

import javax.persistence.*;

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

    @Column (name = "cipher", unique = true)
    private String cipher;

    @Column (name = "route")
    private String route;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "type")
    private Type type;

    @JoinColumn (name = "product")
    @ManyToOne
    private Product mainProduct;

    public enum Type{
        DETAIL ("Деталь"),
        ASSEMBLY ("Сборочная единица"),
        PURCHASED ("Покупная"),
        MATERIAL ("Материал"),
        NORMALIZED ("Стандарное изделие");

        private String type;

        Type(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }
    }
}
