package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Comment extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @JoinColumn(name = "seller_id")
    @ManyToOne
    Seller seller;

    @Column
    String avatar;

    @Column
    String content;
}
