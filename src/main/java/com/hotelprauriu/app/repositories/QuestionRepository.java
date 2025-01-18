package com.hotelprauriu.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import com.hotelprauriu.app.entities.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    
    @Override
    @NonNull
    List<Question> findAll();
    
}
