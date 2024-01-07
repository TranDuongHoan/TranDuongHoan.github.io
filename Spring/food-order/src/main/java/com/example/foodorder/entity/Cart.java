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
@Table(name = "carts")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Cart extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    User user;

}
