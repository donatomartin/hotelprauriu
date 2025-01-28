package com.hotelprauriu.app.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.hotelprauriu.app.entities.Message;

public interface MessageRepository extends CrudRepository<Message, UUID> {

    Page<Message> findAll(Pageable pageable);

}
