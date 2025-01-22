package com.hotelprauriu.app.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.entities.Message;
import com.hotelprauriu.app.services.MessageService;
import com.hotelprauriu.app.services.ReservationService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ReservationService reservationService;
    private final MessageService messageService;

    public AdminController(ReservationService reservationService, MessageService messageService) {
        this.reservationService = reservationService;
        this.messageService = messageService;
    }

    // Full Admin Panel Page
    @RequestMapping(value = { "*", "/dashboard" }, method = RequestMethod.GET)
    public String getAdminPanel() {
        return "admin/pages/home/dashboard";
    }

    @RequestMapping(value = "/inbox", method = RequestMethod.GET)
    public String getInbox(
            Model model,
            @Qualifier("reservation") @PageableDefault(page = 0, size = 4) Pageable reservationPageable,
            @Qualifier("message") @PageableDefault(page = 0, size = 4) Pageable messagePageable,
            @RequestParam(value = "reservation_page", defaultValue = "0") int reservationPage,
            @RequestParam(value = "message_page", defaultValue = "0") int messagePage) {

        // Create custom pageable for reservations and messages
        Pageable reservationPaging = PageRequest.of(reservationPage, reservationPageable.getPageSize());
        Pageable messagePaging = PageRequest.of(messagePage, messagePageable.getPageSize());

        // Fetch the paginated data
        Page<Reservation> reservationList = reservationService.findAll(reservationPaging);
        Page<Message> messageList = messageService.findAll(messagePaging);

        // Add data to the model
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("messageList", messageList);

        return "admin/pages/home/inbox";
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String getReservations(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 4) Pageable pageable) {

        Pageable paging = PageRequest.of(page, pageable.getPageSize());
        Page<Reservation> reservationList = reservationService.findAll(paging);

        model.addAttribute("reservationList", reservationList);

        // Return the Thymeleaf fragment
        return "admin/fragments/tables/reservations";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String getmessages(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 4) Pageable pageable) {

        Pageable paging = PageRequest.of(page, pageable.getPageSize());
        Page<Message> messageList = messageService.findAll(paging);

        model.addAttribute("messageList", messageList);

        // Return the Thymeleaf fragment
        return "admin/fragments/tables/messages";
    }

}
