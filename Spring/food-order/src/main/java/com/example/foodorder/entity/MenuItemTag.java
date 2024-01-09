package com.example.foodorder.entity;

import jakarta.persistence.Entity;
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

    String tagName;

}
