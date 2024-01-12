package com.example.foodorder.entity;



import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_category")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FoodCategory extends BaseEntity{

    @JoinColumn(name = "seller_id")
    @ManyToOne(targetEntity = Seller.class)
    Seller seller;

    @JoinColumn(name = "menu_id")
    @ManyToOne(targetEntity = Menu.class)
    Menu menu;

    @Column(name = "food_category_name")
    String name;

}
