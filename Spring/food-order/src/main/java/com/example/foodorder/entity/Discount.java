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

    @Column(name = "discount_name")
    String name;

    @Column(name = "discount_value")
    Integer discountValue;

    @Column(name = "discount_unit")
    Integer discountUnit;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;
}
