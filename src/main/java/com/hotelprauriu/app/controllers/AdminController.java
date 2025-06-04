package com.hotelprauriu.app.controllers;

import com.hotelprauriu.app.entities.Template;
import com.hotelprauriu.app.services.TemplateService;
import com.hotelprauriu.app.services.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

  private final TemplateService templateService;
  private final LogService logService;

  public AdminController(TemplateService templateService, LogService logService) {
    this.templateService = templateService;
    this.logService = logService;
  }

  // Full Admin Panel Page
  @GetMapping("/admin")
  public String getAdminPanel() {
    return "admin/pages/home/admin";
  }

  @GetMapping("/admin/templates")
  public String getTemplates(Model model) {
    model.addAttribute("template", new Template());
    model.addAttribute("templates", templateService.findAll());
    return "admin/pages/home/templates";
  }

  @PostMapping("/admin/templates")
  public String postTemplate(@Validated Template template, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("templates", templateService.findAll());
      return "admin/pages/home/templates";
    }
    templateService.save(template);
    return "redirect:/admin/templates";
  }

  @GetMapping("/admin/templates/edit/{id}")
  public String editTemplate(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id, Model model) {
    Template template = templateService.findById(id);
    model.addAttribute("template", template);
    return "admin/pages/home/edit-template";
  }

  @PostMapping("/admin/templates/edit/{id}")
  public String updateTemplate(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id,
      @Validated Template template,
      BindingResult result) {
    if (result.hasErrors()) {
      return "admin/pages/home/edit-template";
    }
    template.setId(id);
    templateService.save(template);
    return "redirect:/admin/templates";
  }

  @PostMapping("/admin/templates/delete/{id}")
  public String deleteTemplate(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id) {
    templateService.delete(id);
    return "redirect:/admin/templates";
  }

  @GetMapping("/admin/logs")
  public String getLogs(Model model) {
    model.addAttribute("logs", logService.findAll());
    return "admin/pages/home/logs";
  }

  @GetMapping("/admin/settings")
  public String getSettings() {
    return "admin/pages/home/settings";
  }
}
