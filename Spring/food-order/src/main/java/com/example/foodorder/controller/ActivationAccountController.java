package com.example.foodorder.controller;

import com.example.foodorder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor

public class ActivationAccountController {

    UserService userService;

    @GetMapping("/accounts/{id}/activation")
    public String getAccountActivationPage(@PathVariable Long id, Model model) {
        userService.verifyAccount(id);
        return "redirect:/account_activation_successful";
    }
}
