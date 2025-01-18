package com.hotelprauriu.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.repositories.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

}
