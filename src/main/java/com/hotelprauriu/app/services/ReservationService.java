package com.hotelprauriu.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.repositories.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void deleteAll() {
        reservationRepository.deleteAll();
    }

    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation) {

        if (reservation.getResponse() == null)
            reservation.setResponse("");

        reservationRepository.save(reservation);
    }

}
