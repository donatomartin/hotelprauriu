package com.hotelprauriu.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.hotelprauriu.app.entities.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);
    
}
