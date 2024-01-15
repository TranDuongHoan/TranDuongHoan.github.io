package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_favorite")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserFavorite extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @JoinColumn(name = "seller_id")
    @ManyToOne
    Seller seller;
}
