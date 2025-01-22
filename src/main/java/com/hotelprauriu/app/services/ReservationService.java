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

  public Page<Reservation> findAll(Pageable pageable) {
    return reservationRepository.findAll(pageable);
  }

  public void addReservation(Reservation reservation) {
    reservationRepository.save(reservation);
  }

}
