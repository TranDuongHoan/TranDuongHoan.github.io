package com.example.foodorder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_menu_item")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderMenuItem extends BaseEntity{

    @Column
    Integer quantity;

}
