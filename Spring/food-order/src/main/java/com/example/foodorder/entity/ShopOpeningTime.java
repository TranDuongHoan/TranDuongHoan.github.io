package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop_opening_time")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ShopOpeningTime extends BaseEntity{

    @JoinColumn(name = "seller_id")
    @ManyToOne
    Seller seller;

    @Column(name = "from_day")
    LocalDateTime fromDay;

    @Column(name = "to_day")
    LocalDateTime toDay;

    @Column(name = "from_time")
    LocalDateTime fromTime;

    @Column(name = "to_time")
    LocalDateTime toTime;
}
