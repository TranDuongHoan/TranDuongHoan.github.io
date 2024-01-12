package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "from_day")
    LocalDate fromDay;

    @Column(name = "to_day")
    LocalDate toDay;

    @Column(name = "from_time")
    LocalTime fromTime;

    @Column(name = "to_time")
    LocalTime toTime;
}
