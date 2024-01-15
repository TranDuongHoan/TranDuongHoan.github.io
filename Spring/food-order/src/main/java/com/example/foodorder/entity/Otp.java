package com.example.foodorder.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otps")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Otp extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @JoinColumn(name = "seller_id")
    @ManyToOne
    Seller seller;

    @Column(name = "confirm")
    boolean confirm;

    @Column(name = "confirmation_code")
    String confirmationCode;

    @Column(name = "confirmation_code_expired_in")
    LocalDateTime confirmationCodeExpiredIn;




}
