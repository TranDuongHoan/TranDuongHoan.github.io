package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshToken extends BaseEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(columnDefinition = "boolean default false")
    boolean invalidated;

}
