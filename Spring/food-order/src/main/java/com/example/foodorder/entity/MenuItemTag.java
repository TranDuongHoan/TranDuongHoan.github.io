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
@Table(name = "menuitemtags")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuItemTag extends BaseEntity{

    @JoinColumn(name = "menu_item_id")
    @ManyToOne(targetEntity = MenuItem.class)
    MenuItem menuItem;

    String tagName;

}
