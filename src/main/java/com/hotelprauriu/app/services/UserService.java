package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.User;
import com.hotelprauriu.app.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final RolesService rolesService;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Value("${hotelprauriu.admin.username}")
  private String adminUsername;

  @Value("${hotelprauriu.admin.password}")
  private String adminPassword;

  @Value("${hotelprauriu.admin.email}")
  private String adminEmail;

  public UserService(
      RolesService rolesService,
      UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.rolesService = rolesService;
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostConstruct
  public void init() {
    ensureAdminExists();
  }

  public void ensureAdminExists() {
    User admin = userRepository.findByUsername(adminUsername);
    if (admin == null) {
      System.out.println("ADMIN DIDN'T EXIST: CREATING PROFILE...");

      admin = new User();
      admin.setUsername(adminUsername);
      admin.setPassword(adminPassword);
      admin.setEmail(adminEmail);
      admin.setRole(rolesService.getRoles()[0]);
      addUser(admin);
    }
  }

  public void deleteAll() {
    userRepository.deleteAll();
  }

  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  public void addUser(User user) {
    if (user.getPassword() != null) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);
    }
  }
}
