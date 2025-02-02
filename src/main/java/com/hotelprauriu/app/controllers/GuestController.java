package com.hotelprauriu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {

    @GetMapping("/")
    public String getIndex() {
        return "guest/pages/home/index";
    }

    @GetMapping("/colabs")
    public String getColabs() {
        return "guest/pages/colabs/colabs";
    }

    @GetMapping("/rules")
    public String getRules() {
        return "guest/pages/info/rules";
    }

    @GetMapping("/privacy")
    public String getPrivacy() {
        return "guest/pages/info/privacy";
    }

    @GetMapping("/sustainability")
    public String getSustainability() {
        return "guest/pages/info/sustainability";
    }

}
