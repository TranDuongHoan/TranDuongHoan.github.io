package com.example.foodorder.repository;


import com.example.foodorder.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPJpaRepository extends JpaRepository<OTP, Long> {

    OTP findByOtp(String otp);


}
