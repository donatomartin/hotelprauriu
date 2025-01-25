package com.hotelprauriu.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hotelprauriu.app.entities.Log;

@Repository
public interface LoggerRepository extends CrudRepository<Log, Long> {

    @Query("SELECT l from Log l ORDER BY l.date DESC")
    Page<Log> findAllOrdered(Pageable pageable);

    @Query("SELECT l from Log l WHERE LOWER(l.action) like LOWER(?1) ORDER BY l.date DESC")
    Page<Log> findAllByAction(String action, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE from Log l WHERE LOWER(l.action) like LOWER(?1)")
    void deleteByAction(String action);

}