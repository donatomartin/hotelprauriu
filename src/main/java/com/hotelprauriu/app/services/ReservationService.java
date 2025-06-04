package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.repositories.ReservationRepository;
import com.hotelprauriu.app.services.LogService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final LogService logService;

  public ReservationService(ReservationRepository reservationRepository, LogService logService) {
    this.reservationRepository = reservationRepository;
    this.logService = logService;
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

  public Optional<Reservation> findById(UUID id) {
    return reservationRepository.findById(id);
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
    logService.log("RESERVATION", "Nueva reserva de " + reservation.getGuestFullName());
  }

  public void acceptReservation(UUID id) {

    Reservation reservation = reservationRepository.findById(id).get();
    reservation.setStatus(Reservation.Status.ACCEPTED);
    reservationRepository.save(reservation);
    logService.log("RESERVATION", "Reserva aceptada " + id);
  }

  public void refuseReservation(UUID id) {

    Reservation reservation = reservationRepository.findById(id).get();
    reservation.setStatus(Reservation.Status.REFUSED);
    reservationRepository.save(reservation);
    logService.log("RESERVATION", "Reserva rechazada " + id);
  }

  public void discardReservation(UUID id) {

    Reservation reservation = reservationRepository.findById(id).get();
    reservation.setStatus(Reservation.Status.DISCARDED);
    reservationRepository.save(reservation);
    logService.log("RESERVATION", "Reserva descartada " + id);
  }

  public void deleteReservation(UUID id) {
    reservationRepository.deleteById(id);
    logService.log("RESERVATION", "Reserva eliminada " + id);
  }
}
