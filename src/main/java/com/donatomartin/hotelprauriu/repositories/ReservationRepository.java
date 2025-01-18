package com.donatomartin.hotelprauriu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import com.donatomartin.hotelprauriu.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
    @Override
    @NonNull
    List<Reservation> findAll();
    
}
