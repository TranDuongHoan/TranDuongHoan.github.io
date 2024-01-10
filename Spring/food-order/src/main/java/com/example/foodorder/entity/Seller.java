package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sellers")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Seller extends  BaseEntity{

    @JoinColumn(name = "cart_id")
    @ManyToOne(targetEntity = Cart.class)
    Cart cart;

    @Column
    String shop_name;

    @Column
    String address;

    @Column
    String image;

    @Column
    String rating;



}
