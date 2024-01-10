package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuItem extends BaseEntity{

    @JoinColumn(name = "menu_group_id")
    @ManyToOne(targetEntity = MenuGroup.class)
    MenuGroup menuGroup;

    @JoinColumn(name = "cart_id")
    @ManyToOne(targetEntity = Cart.class)
    Cart cart;

    @Column(name = "menu_item_name")
    String name;

    @Column(name = "original_price")
    String originalPrice;

    @Column(name = "actual_price")
    String actualPrice;

    @Column(name = "image")
    String image;

    @Column(name = "description")
    String description;

    @ManyToMany(mappedBy = "order_menu_item")
    private List<Order> orders;

    @ManyToMany(mappedBy = "cart_menu_item")
    private List<Cart> carts;
}
