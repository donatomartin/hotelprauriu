package com.hotelprauriu.app.controllers;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GuestController {

  @GetMapping("/")
  public Map<String, Object> getIndex(
      @org.springframework.web.bind.annotation.RequestParam(required = false)
          boolean reservationSuccess) {
    return Map.of("reservationSuccess", reservationSuccess);
  }

  @GetMapping("/colabs")
  public Map<String, String> getColabs() {
    return Map.of("page", "colabs");
  }

  @GetMapping("/rules")
  public Map<String, String> getRules() {
    return Map.of("page", "rules");
  }

  @GetMapping("/privacy")
  public Map<String, String> getPrivacy() {
    return Map.of("page", "privacy");
  }

  @GetMapping("/sustainability")
  public Map<String, String> getSustainability() {
    return Map.of("page", "sustainability");
  }

  @GetMapping("/terms")
  public Map<String, String> getTerms() {
    return Map.of("page", "terms");
  }
}
