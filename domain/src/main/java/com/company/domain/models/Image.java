package com.company.domain.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "bite_code")
    private String biteCode;
}
