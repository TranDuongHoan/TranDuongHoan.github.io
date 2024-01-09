package com.example.foodorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @JoinColumn(name = "food_category_id")
    @ManyToOne(targetEntity = FoodCategory.class)
    FoodCategory foodcategory;

    @JoinColumn(name = "cart_id")
    @ManyToOne(targetEntity = Cart.class)
    Cart cart;

    String shop_name;

    String address;

    String image;

    String rating;



}
