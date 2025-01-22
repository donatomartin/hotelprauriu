package com.hotelprauriu.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Message;
import com.hotelprauriu.app.repositories.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Page<Message> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
    
}
