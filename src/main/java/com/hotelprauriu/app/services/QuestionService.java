package com.hotelprauriu.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Question;
import com.hotelprauriu.app.repositories.QuestionRepository;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
    
}
