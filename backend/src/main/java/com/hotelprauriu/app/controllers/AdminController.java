package com.hotelprauriu.app.controllers;

import com.hotelprauriu.app.entities.Template;
import com.hotelprauriu.app.services.TemplateService;
import com.hotelprauriu.app.services.LogService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

  private final TemplateService templateService;
  private final LogService logService;

  public AdminController(TemplateService templateService, LogService logService) {
    this.templateService = templateService;
    this.logService = logService;
  }

  // Full Admin Panel Page
  @GetMapping("")
  public Map<String, String> getAdminPanel() {
    return Map.of("status", "ok");
  }

  @GetMapping("/templates")
  public Iterable<Template> getTemplates() {
    return templateService.findAll();
  }

  @PostMapping("/templates")
  public ResponseEntity<?> postTemplate(@Validated @RequestBody Template template, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(result.getAllErrors());
    }
    templateService.save(template);
    return ResponseEntity.ok(template);
  }

  @GetMapping("/templates/{id}")
  public ResponseEntity<Template> editTemplate(@PathVariable java.util.UUID id) {
    Template template = templateService.findById(id);
    if (template == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(template);
  }

  @PutMapping("/templates/{id}")
  public ResponseEntity<?> updateTemplate(@PathVariable java.util.UUID id,
      @Validated @RequestBody Template template,
      BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(result.getAllErrors());
    }
    template.setId(id);
    templateService.save(template);
    return ResponseEntity.ok(template);
  }

  @DeleteMapping("/templates/{id}")
  public ResponseEntity<?> deleteTemplate(@PathVariable java.util.UUID id) {
    templateService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/logs")
  public Iterable<com.hotelprauriu.app.entities.LogEntry> getLogs() {
    return logService.findAll();
  }

  @GetMapping("/settings")
  public Map<String,String> getSettings() {
    return Map.of("settings", "ok");
  }
}
