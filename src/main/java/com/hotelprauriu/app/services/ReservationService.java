package com.hotelprauriu.app.services;

import java.util.UUID;

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

    public Page<Reservation> findByStatus(Pageable pageable, Reservation.Status status) {
        return reservationRepository.findByStatus(pageable, status);

    }

    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation) {

        if (reservation.getResponse() == null) {
            reservation.setResponse("");
        }

        if (reservation.getGuestMessage() == null) {
            reservation.setGuestMessage("");
        }

        reservation.setStatus(Reservation.Status.PENDING);

        reservationRepository.save(reservation);
    }
    
    public void acceptReservation(UUID id) {

        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStatus(Reservation.Status.ACCEPTED);
        reservationRepository.save(reservation);

    }

    public void refuseReservation(UUID id) {

        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStatus(Reservation.Status.REFUSED);
        reservationRepository.save(reservation);
    
    }

    public void discardReservation(UUID id) {

        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStatus(Reservation.Status.DISCARDED);
        reservationRepository.save(reservation);
    
    }

}
