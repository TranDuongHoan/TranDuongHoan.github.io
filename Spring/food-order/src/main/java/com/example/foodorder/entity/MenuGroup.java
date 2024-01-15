package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_group")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuGroup extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "menu_id")
    Menu menu;

    @Column(name = "menu_group_name")
    String name;

    @Column(name = "image")
    String image;

    @Column(name = "[order]")
    Integer order;
}
