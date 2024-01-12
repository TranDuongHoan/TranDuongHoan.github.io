package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_menu_item")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CartMenuItem extends BaseEntity{

    @Column(name = "quantity")
    Integer quantity;

}
