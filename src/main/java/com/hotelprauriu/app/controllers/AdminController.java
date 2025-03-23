package com.hotelprauriu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

  // Full Admin Panel Page
  @GetMapping("/admin")
  public String getAdminPanel() {
    return "admin/pages/home/admin";
  }

  @GetMapping("/admin/templates")
  public String getTemplates() {
    return "admin/pages/home/templates";
  }

  @GetMapping("/admin/settings")
  public String getSettings() {
    return "admin/pages/home/settings";
  }
}
