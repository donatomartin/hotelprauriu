package com.donatomartin.hotelprauriu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestController {
    
    @RequestMapping(value={"/index", "/", "/home"}, method=RequestMethod.GET)
    public String getIndex() {
        return "guest/pages/home/index";
    }

    @RequestMapping(value={"/reservation"}, method=RequestMethod.GET)
    public String getReservation() {
        return "guest/pages/reservations/reservation";
    }

    @RequestMapping(value={"/colabs"}, method=RequestMethod.GET)
    public String getColabs() {
        return "guest/pages/colabs/colabs";
    }

    @RequestMapping(value={"/rules"}, method=RequestMethod.GET)
    public String getRules() {
        return "guest/pages/info/rules";
    }

    @RequestMapping(value={"/privacy"}, method=RequestMethod.GET)
    public String getPrivacy() {
        return "guest/pages/info/privacy";
    }
    
    @RequestMapping(value={"/sustainability"}, method=RequestMethod.GET)
    public String getSustainability() {
        return "guest/pages/info/sustainability";
    }
    
}
