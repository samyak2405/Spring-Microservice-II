package com.samyak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="productId")
    private long productId;

    @Column(name="quantity")
    private long quantity;

    @Column(name= "order_date")
    private Instant orderDate;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="total_amount")
    private long amount;
}
