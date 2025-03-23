package com.hotelprauriu.app.controllers;

import com.hotelprauriu.app.entities.User;
import com.hotelprauriu.app.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/admin/users")
  public String getUsers(
      Model model,
      @PageableDefault(size = 5) Pageable pageable,
      @RequestParam(defaultValue = "0") int page) {

    Pageable paging = PageRequest.of(page, pageable.getPageSize());

    Page<User> reservationList;
    reservationList = userService.findAll(paging);

    model.addAttribute("page", reservationList);

    return "admin/pages/home/users";
  }
}
