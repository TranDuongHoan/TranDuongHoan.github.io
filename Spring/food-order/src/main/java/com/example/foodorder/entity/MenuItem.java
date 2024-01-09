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
@Table(name = "menuitems")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuItem extends BaseEntity{

    @JoinColumn(name = "menu_group_id")
    @ManyToOne(targetEntity = MenuGroup.class)
    MenuGroup menuGroup;

    String name;

    String originalPrice;

    String actualPrice;

    String image;

    String description;
}
