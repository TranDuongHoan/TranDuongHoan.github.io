package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Order extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    User user;

    @JoinColumn(name = "seller_id")
    @ManyToOne(targetEntity = Seller.class)
    Seller seller;

    @Column
    String orderStatus;

    @Column
    String discountCode;

    @Column
    LocalDateTime shippingStartTime;

    @Column
    LocalDateTime completionTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_menu_item",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<MenuItem> menuItems = new HashSet<>();
}
