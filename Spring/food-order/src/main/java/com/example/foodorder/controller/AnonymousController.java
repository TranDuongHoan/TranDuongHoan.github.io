package com.example.foodorder.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AnonymousController {

    @GetMapping("/restaurant")
    public String getAboutPage(Model model) {
        return "restaurant";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        return "signup";
    }

    @GetMapping("/signin")
    public String getSigninPage(Model model) {
        return "signin";
    }

    @GetMapping("/verification")
    public String getVerificationPage(Model model) {
        return "verification";
    }

    @GetMapping("/forgot_password")
    public String getForgotPasswordPage(Model model) {
        return "forgot_password";
    }

    @GetMapping("/change_password")
    public String getChangePasswordPage(Model model) {
        return "change_password";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model) {
        return "checkout";
    }

    @GetMapping("/location")
    public String getLocationPage(Model model) {
        return "location";
    }

    @GetMapping("/search")
    public String getSearchPage(Model model) {
        return "search";
    }

    @GetMapping("/successful")
    public String getSuccessfulPage(Model model) {
        return "successful";
    }

    @GetMapping("/map")
    public String getMapPage(Model model) {
        return "map";
    }

    @GetMapping("/trending")
    public String getTrendingPage(Model model) {
        return "trending";
    }

    @GetMapping("/offers")
    public String getOffersPage(Model model) {
        return "offers";
    }

    @GetMapping("/faq")
    public String getFAQPage(Model model) {
        return "faq";
    }

    @GetMapping("/not-found")
    public String getErrorPage(Model model) {
        return "not-found";
    }

}
