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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
  public String getReservation(Model model) {
    model.addAttribute("reservation", new Reservation());
    return "guest/pages/reservations/reservation";
  }

  @PostMapping("/reservation")
  public String postReservation(
      Model model, @Validated Reservation reservation, BindingResult result) {

    if (result.hasErrors()) {

      List<String> errors =
          result.getAllErrors().stream()
              .map(ObjectError::getDefaultMessage)
              .collect(Collectors.toList());

      model.addAttribute("errors", errors);
      model.addAttribute("reservation", reservation);
      return "guest/pages/reservations/reservation";
    }

    mailService.sendMailsAboutReservation(reservation);
    reservationService.addReservation(reservation);

    return "redirect:/?reservationSuccess=true";
  }

  @GetMapping("/admin/reservations")
  public String getReservations(
      Model model,
      @PageableDefault(size = 5) Pageable pageable,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "PENDING") String filter) {

    Pageable paging =
        PageRequest.of(page, pageable.getPageSize(), Sort.by("lastUpdated").descending());

    Page<Reservation> reservationList;
    reservationList = reservationService.findByStatus(paging, Reservation.Status.valueOf(filter));

    model.addAttribute("filter", filter);
    model.addAttribute("page", reservationList);

    return "admin/pages/home/reservations";
  }

  @GetMapping("/admin/reservation/modal/{id}")
  public String getReservationModal(@PathVariable UUID id, Model model) {
    Reservation reservation = reservationService.findById(id).orElse(null);
    // TODO: validate not null
    model.addAttribute("reservation", reservation);
    model.addAttribute("templates", templateService.findAll());
    return "admin/fragments/reservation-modal :: modalContent";
  }

  @PostMapping("/admin/reservation/accept")
  public String acceptReservation(
      @RequestParam UUID id, @RequestParam(required = false) String response) {
    Reservation r = reservationService.findById(id).orElse(null);
    if (r != null) {
      r.setResponse(response == null ? "" : response);
      reservationService.acceptReservation(id);
      mailService.sendResponseMail(r);
    }
    return "redirect:/admin/reservations";
  }

  @PostMapping("/admin/reservation/decline")
  public String declineReservation(
      @RequestParam UUID id, @RequestParam(required = false) String response) {
    Reservation r = reservationService.findById(id).orElse(null);
    if (r != null) {
      r.setResponse(response == null ? "" : response);
      reservationService.refuseReservation(id);
      mailService.sendResponseMail(r);
    }
    return "redirect:/admin/reservations";
  }

  @PostMapping("/admin/reservation/discard")
  public String discardReservation(@RequestParam UUID id) {

    reservationService.discardReservation(id);

    return "redirect:/admin/reservations";
  }

  @PostMapping("/admin/reservation/delete")
  public String deleteReservation(@RequestParam UUID id) {

    reservationService.deleteReservation(id);

    return "redirect:/admin/reservations";
  }
}
