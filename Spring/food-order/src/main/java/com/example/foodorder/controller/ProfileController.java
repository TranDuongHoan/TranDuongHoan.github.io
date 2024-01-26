package com.example.foodorder.controller;


import com.example.foodorder.exception.*;
import com.example.foodorder.model.request.ForgotPasswordRequest;
import com.example.foodorder.model.request.MailRequest;
import com.example.foodorder.model.request.UpdatePasswordRequest;
import com.example.foodorder.model.request.VerificationPasswordRequest;
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

    @GetMapping("/forgot_password")
    public String getForgotPasswordPage(Model model) {
        return "profile/forgot_password";
    }

    @GetMapping("/verification")
    public String getVerificationPage(Model model) {
        return "profile/verification";
    }


    @PutMapping("/change_password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UpdatePasswordRequest request)
            throws UserNotFoundException, PasswordNotMatchedException {
        userService.changePassword(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> otpSending(@RequestBody @Valid MailRequest request) throws MessagingException {
        String otp = otpService.createOTP(request.getEmail());
        String fullName = userService.getUserName(request.getEmail());
        emailService.otpSendingMail(fullName, request.getEmail(), otp);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/verification")
    public ResponseEntity<?> verificationPassword(@RequestBody @Valid VerificationPasswordRequest request) throws UserNotFoundException,
            OTPNotMatchedException, PasswordNotMatchedException, OTPNotFoundException, OTPExpiredException {
        userService.verificationPassword(request);
        return ResponseEntity.ok(null);
    }




}
