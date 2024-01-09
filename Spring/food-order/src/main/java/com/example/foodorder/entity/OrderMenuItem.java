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
@Table(name = "ordermenuitems")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderMenuItem extends BaseEntity{

}
