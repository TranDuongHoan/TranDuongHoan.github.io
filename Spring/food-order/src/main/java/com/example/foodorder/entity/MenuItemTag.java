package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_tag")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MenuItemTag extends BaseEntity{

    @JoinColumn(name = "menu_item_id")
    @ManyToOne
    MenuItem menuItem;

    @Column(name = "tag_name")
    String tagName;

}
