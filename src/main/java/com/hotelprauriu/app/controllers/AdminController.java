package com.hotelprauriu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    // Full Admin Panel Page
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String getAdminPanel() {
        return "admin/pages/home/admin";
    }

    @RequestMapping(value = "/admin/inbox", method = RequestMethod.GET)
    public String getInbox() {
        return "admin/pages/home/inbox";
    }

    @RequestMapping("/admin/templates")
    public String getTemplates() {
        return "admin/pages/home/templates";
    }

    @RequestMapping("/admin/settings")
    public String getSettings() {
        return "admin/pages/home/settings";
    }

    @RequestMapping("/admin/users")
    public String getUsers() {
        return "admin/pages/home/users";
    }

}
