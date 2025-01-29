package com.hotelprauriu.app.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.services.MailService;
import com.hotelprauriu.app.services.ReservationService;

@Controller
public class ReservationController {

    public ReservationService reservationService;
    public MailService mailService;

    public ReservationController(

            ReservationService reservationService,
            MailService mailService

    ) {

        this.reservationService = reservationService;
        this.mailService = mailService;

    }

    @RequestMapping(value = { "/reservation" }, method = RequestMethod.GET)
    public String getReservation() {
        return "guest/pages/reservations/reservation";
    }

    @RequestMapping(value = { "/reservation" }, method = RequestMethod.POST)
    public String postReservation(@Validated Reservation reservation) {

        mailService.sendMailsAboutReservation(reservation);
        reservationService.addReservation(reservation);

        return "guest/pages/home/index";
    }

    @RequestMapping("/admin/reservations")
    public String getReservations(
        Model model,
        @PageableDefault(size = 5) Pageable pageable,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "PENDING") String filter
    ) {

        Pageable paging = PageRequest.of(page, pageable.getPageSize(), Sort.by("lastUpdated").descending());
        
        Page<Reservation> reservationList;
        reservationList = reservationService.findByStatus(paging, Reservation.Status.valueOf(filter));

        model.addAttribute("filter", filter);
        model.addAttribute("page", reservationList);

        return "admin/pages/home/reservations";
    }

    @RequestMapping(value = "/admin/reservation/accept", method = RequestMethod.POST)
    public String acceptReservation(
            @RequestParam UUID id) {

        reservationService.acceptReservation(id);

        return "redirect:/admin/reservations";
    }

    @RequestMapping(value = "/admin/reservation/decline", method = RequestMethod.POST)
    public String declineReservation(
            @RequestParam UUID id) {

        reservationService.refuseReservation(id);

        return "redirect:/admin/reservations";
    }

    @RequestMapping(value = "/admin/reservation/discard", method = RequestMethod.POST)
    public String discardReservation(
            @RequestParam UUID id) {

        reservationService.discardReservation(id);

        return "redirect:/admin/reservations";
    }

}
