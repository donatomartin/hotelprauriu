package com.donatomartin.hotelprauriu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.donatomartin.hotelprauriu.entities.Reservation;
import com.donatomartin.hotelprauriu.repositories.ReservationRepository;

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
