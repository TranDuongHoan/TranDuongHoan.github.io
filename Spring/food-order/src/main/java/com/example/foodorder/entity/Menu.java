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
@Table(name = "menus")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Menu extends BaseEntity{

    @JoinColumn(name = "seller_id")
    @ManyToOne(targetEntity = Seller.class)
    Seller seller;

    @JoinColumn(name = "cart_id")
    @ManyToOne(targetEntity = Cart.class)
    Cart cart;

}
