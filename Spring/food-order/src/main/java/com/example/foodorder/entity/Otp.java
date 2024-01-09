package com.example.foodorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otps")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Otp extends BaseEntity{

    boolean confirm;

    String confirmation_code;

    LocalDateTime confirmationCodeExpiredIn;




}
