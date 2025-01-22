package com.hotelprauriu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hotelprauriu.app.entities.Message;
import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.services.MessageService;
import com.hotelprauriu.app.services.ReservationService;

@Controller
public class GuestController {

  private final ReservationService reservationService;
  private final MessageService messageService;

  public GuestController(ReservationService reservationService, MessageService messageService) {
    this.reservationService = reservationService;
    this.messageService = messageService;
  }

  @RequestMapping(value = { "/index", "/", "/home" }, method = RequestMethod.GET)
  public String getIndex() {
    return "guest/pages/home/index";
  }

  @RequestMapping(value = { "/reservation" }, method = RequestMethod.GET)
  public String getReservation() {
    return "guest/pages/reservations/reservation";
  }

  @RequestMapping(value = { "/reservation" }, method = RequestMethod.POST)
  public String postReservation(@Validated Reservation reservation) {

    if (reservation.getGuestMessage().isBlank())
      reservation.setGuestMessage(null);

    reservationService.addReservation(reservation);

    return "guest/pages/home/index";
  }

  @RequestMapping(value = { "/message" }, method = RequestMethod.POST)
  public String postMessage(@Validated Message message) {

    messageService.addMessage(message);

    return "guest/pages/home/index";
  }

  @RequestMapping(value = { "/colabs" }, method = RequestMethod.GET)
  public String getColabs() {
    return "guest/pages/colabs/colabs";
  }

  @RequestMapping(value = { "/rules" }, method = RequestMethod.GET)
  public String getRules() {
    return "guest/pages/info/rules";
  }

  @RequestMapping(value = { "/privacy" }, method = RequestMethod.GET)
  public String getPrivacy() {
    return "guest/pages/info/privacy";
  }

  @RequestMapping(value = { "/sustainability" }, method = RequestMethod.GET)
  public String getSustainability() {
    return "guest/pages/info/sustainability";
  }

}
