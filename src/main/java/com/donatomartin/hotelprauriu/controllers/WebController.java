package com.donatomartin.hotelprauriu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WebController {
    
    @RequestMapping(value={"/index", "/"}, method=RequestMethod.GET)
    public String getIndex() {
        return "pages/index";
    }

    @RequestMapping(value={"/rules"}, method=RequestMethod.GET)
    public String getRules() {
        return "pages/rules";
    }
    
    

}
