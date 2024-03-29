package com.example.foodorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String getBackHomePage(Model model) {
        return "home";
    }

}
