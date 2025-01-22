package com.hotelprauriu.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.hotelprauriu.app.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

  Page<Reservation> findAll(Pageable pageable);

}
