package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop_opening_time")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ShopOpeningTime extends BaseEntity{

    @JoinColumn(name = "seller_id")
    @ManyToOne(targetEntity = Seller.class)
    Seller seller;

    @Column
    LocalDate fromDay;

    @Column
    LocalDate toDay;

    @Column
    LocalTime fromTime;

    @Column
    LocalTime toTime;
}
