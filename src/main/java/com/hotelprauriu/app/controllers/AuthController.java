package com.hotelprauriu.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    
    @GetMapping("login")
    public String login(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Authentication authentication, Model model) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/"; // redirects to the home page if the user is already authenticated
        }

        if (error != null) {
            model.addAttribute("loginerror", true);
        }

        if (logout != null) {
            model.addAttribute("logout", true);
        }

        return "shared/pages/auth/login";
    }

    

}
