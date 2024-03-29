package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @ManyToOne
    User user;

    @JoinColumn(name = "seller_id")
    @ManyToOne
    Seller seller;

    @Column(name = "order_status")
    String orderStatus;

    @Column(name = "discount_code")
    String discountCode;

    @Column(name = "shipping_start_time")
    LocalDateTime shippingStartTime;

    @Column(name = "completion_time")
    LocalDateTime completionTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_menu_item",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<MenuItem> menuItems = new HashSet<>();
}
