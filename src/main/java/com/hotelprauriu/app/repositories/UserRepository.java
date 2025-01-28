package com.hotelprauriu.app.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.hotelprauriu.app.entities.User;

public interface UserRepository extends CrudRepository<User, UUID> {

    Page<User> findAll(Pageable pageable);

    User findByEmail(String email);

    User findByUsername(String username);

}
