package com.example.foodorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    String name;

    Integer discountValue;

    Integer discountUnit;

    LocalDateTime startTime;

    LocalDateTime endTime;
}
