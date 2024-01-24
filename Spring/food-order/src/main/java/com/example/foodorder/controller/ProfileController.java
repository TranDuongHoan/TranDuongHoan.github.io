package com.example.foodorder.controller;


import com.example.foodorder.exception.*;
import com.example.foodorder.model.request.ChangePasswordRequest;
import com.example.foodorder.model.request.MailRequest;
import com.example.foodorder.service.EmailService;
import com.example.foodorder.service.OTPService;
import com.example.foodorder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final EmailService emailService;
    private final OTPService otpService;


    @GetMapping
    public String getProfilePage(Model model) {
        return "profile/profile";
    }

    @GetMapping("/forget-password")
    public String getForgetPasswordPage(Model model) {
        return "profile/forget-password";
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest request)
            throws UserNotFoundException, PasswordNotMatchedException {
        userService.changePassword(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/otp-sending")
    public ResponseEntity<?> otpSending(@RequestBody @Valid MailRequest request) throws MessagingException {
        String otp = otpService.createOTP(request.getEmail());
        String fullName = userService.getUserName(request.getEmail());
        emailService.otpSendingMail(fullName, request.getEmail(), otp);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestBody @Valid ForgetPasswordRequest request) throws UserNotFoundException,
            OTPNotMatchedException, PasswordNotMatchedException, OTPNotFoundException, OTPExpiredException {
        userService.forgetPassword(request);
        return ResponseEntity.ok(null);
    }




}
