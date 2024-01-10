package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_group")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuGroup extends BaseEntity{

    @JoinColumn(name = "menu_id")
    @ManyToOne(targetEntity = Menu.class)
    Menu menu;

    @Column(name = "menu_group_name")
    String name;

    @Column(name = "image")
    String image;

    @Column(name = "order")
    Integer order;
}
