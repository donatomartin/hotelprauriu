package com.hotelprauriu.app.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hotelprauriu.app.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {
    
    Page<Reservation> findAll(Pageable pageable);

    @Query("SELECT r FROM Reservation r WHERE r.status = ?1")
    Page<Reservation> findByStatus(Pageable pageable, Reservation.Status status);

}
