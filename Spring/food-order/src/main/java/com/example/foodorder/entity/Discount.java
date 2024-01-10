package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discounts")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Discount extends BaseEntity{

    @JoinColumn(name = "order_id")
    @ManyToOne(targetEntity = Order.class)
    Order order;

    @Column
    String name;

    @Column
    Integer discountValue;

    @Column
    Integer discountUnit;

    @Column
    LocalDateTime startTime;

    @Column
    LocalDateTime endTime;
}
