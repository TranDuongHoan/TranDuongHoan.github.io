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
@Table(name = "menugroups")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuGroup extends BaseEntity{

    @JoinColumn(name = "menu_id")
    @ManyToOne(targetEntity = Menu.class)
    Menu menu;

    String name;

    String image;

    Integer order;
}
