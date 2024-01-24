package com.example.foodorder.service;


import com.example.foodorder.entity.OTP;
import com.example.foodorder.entity.User;
import com.example.foodorder.repository.OTPJpaRepository;
import com.example.foodorder.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@AllArgsConstructor
public class OTPService {
    private final OTPJpaRepository otpJpaRepository;
    private final UserRepository userRepository;

    private static final long OTP_EXPIRATION_MILLISECOND = 900000; //15 minutes

    public String createOTP(String email) {
        Random random = new Random();
        int newOTP = 10000 + random.nextInt(90000);
        User user = userRepository.findByEmail(email);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plus(OTP_EXPIRATION_MILLISECOND, ChronoUnit.MILLIS);
        OTP otpData = OTP.builder()
                .otp(String.valueOf(newOTP))
                .liveTime(expirationTime)
                .user(user)
                .build();
        otpJpaRepository.save(otpData);
        return String.valueOf(newOTP);
    }


}
