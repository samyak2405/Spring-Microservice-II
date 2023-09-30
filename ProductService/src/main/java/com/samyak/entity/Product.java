package com.samyak.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private long quantity;
}
