package com.hotelprauriu.app.controllers;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.services.MailService;
import com.hotelprauriu.app.services.ReservationService;
import com.hotelprauriu.app.services.TemplateService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ReservationController {

  public ReservationService reservationService;
  public MailService mailService;
  public TemplateService templateService;

  public ReservationController(
      ReservationService reservationService,
      MailService mailService,
      TemplateService templateService) {

    this.reservationService = reservationService;
    this.mailService = mailService;
    this.templateService = templateService;
  }

  @GetMapping("/reservation")
  public Reservation getReservation() {
    return new Reservation();
  }

  @PostMapping("/reservation")
  public ResponseEntity<?> postReservation(
      @Validated @RequestBody Reservation reservation, BindingResult result) {

    if (result.hasErrors()) {

      List<String> errors =
          result.getAllErrors().stream()
              .map(ObjectError::getDefaultMessage)
              .collect(Collectors.toList());
      return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    mailService.sendMailsAboutReservation(reservation);
    reservationService.addReservation(reservation);
    return ResponseEntity.ok(reservation);
  }

  @GetMapping("/admin/reservations")
  public Page<Reservation> getReservations(
      @PageableDefault(size = 5) Pageable pageable,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "PENDING") String filter) {

    Pageable paging =
        PageRequest.of(page, pageable.getPageSize(), Sort.by("lastUpdated").descending());

    Page<Reservation> reservationList;
    reservationList = reservationService.findByStatus(paging, Reservation.Status.valueOf(filter));
    return reservationList;
  }

  @GetMapping("/admin/reservation/{id}")
  public ResponseEntity<?> getReservationModal(@PathVariable UUID id) {
    Reservation reservation = reservationService.findById(id).orElse(null);
    if (reservation == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(Map.of("reservation", reservation, "templates", templateService.findAll()));
  }

  @PostMapping("/admin/reservation/accept")
  public ResponseEntity<?> acceptReservation(
      @RequestParam UUID id, @RequestParam(required = false) String response) {
    Reservation r = reservationService.findById(id).orElse(null);
    if (r != null) {
      r.setResponse(response == null ? "" : response);
      reservationService.acceptReservation(id);
      mailService.sendResponseMail(r);
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/admin/reservation/decline")
  public ResponseEntity<?> declineReservation(
      @RequestParam UUID id, @RequestParam(required = false) String response) {
    Reservation r = reservationService.findById(id).orElse(null);
    if (r != null) {
      r.setResponse(response == null ? "" : response);
      reservationService.refuseReservation(id);
      mailService.sendResponseMail(r);
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/admin/reservation/discard")
  public ResponseEntity<?> discardReservation(@RequestParam UUID id) {

    reservationService.discardReservation(id);

    return ResponseEntity.noContent().build();
  }

  @PostMapping("/admin/reservation/delete")
  public ResponseEntity<?> deleteReservation(@RequestParam UUID id) {

    reservationService.deleteReservation(id);

    return ResponseEntity.noContent().build();
  }
}
