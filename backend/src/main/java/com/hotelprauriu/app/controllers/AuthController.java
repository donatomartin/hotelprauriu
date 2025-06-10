package com.hotelprauriu.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
    
    @GetMapping("/login")
    public Map<String, Object> login(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Authentication authentication) {

        return Map.of(
            "authenticated", authentication != null && authentication.isAuthenticated(),
            "error", error != null,
            "logout", logout != null
        );
    }

    

}
