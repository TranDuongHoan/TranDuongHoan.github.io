package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_tag")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuItemTag extends BaseEntity{

    @JoinColumn(name = "menu_item_id")
    @ManyToOne(targetEntity = MenuItem.class)
    MenuItem menuItem;

    @Column
    String tagName;

}
