package com.hotelprauriu.app.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.hotelprauriu.app.entities.Log;

@Repository
public interface LoggerRepository extends CrudRepository<Log, Long> {

    @Query("SELECT l from Log l ORDER BY l.date DESC")
    List<Log> findAllOrdered();

    @Query("SELECT l from Log l WHERE LOWER(l.action) like LOWER(?1) ORDER BY l.date DESC")
    List<Log> findAllByAction(String action);

    @Modifying
    @Transactional
    @Query("DELETE from Log l WHERE LOWER(l.action) like LOWER(?1)")
    void deleteByAction(String action);

}