package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sellers")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Seller extends  BaseEntity{

    @JoinColumn(name = "cart_id")
    @ManyToOne
    private Cart cart;

    @Column(name = "shop_name")
    String shopName;

    @Column(name = "address")
    String address;

    @Column(name = "image")
    String image;

    @Column(name = "rating")
    String rating;



}
