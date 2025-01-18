package com.donatomartin.hotelprauriu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.donatomartin.hotelprauriu.entities.Question;
import com.donatomartin.hotelprauriu.repositories.QuestionRepository;

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
