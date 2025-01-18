package com.hotelprauriu.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import com.hotelprauriu.app.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
    @Override
    @NonNull
    List<Reservation> findAll();
    
}
