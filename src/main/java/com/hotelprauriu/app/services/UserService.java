package com.hotelprauriu.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.User;
import com.hotelprauriu.app.repositories.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

}
