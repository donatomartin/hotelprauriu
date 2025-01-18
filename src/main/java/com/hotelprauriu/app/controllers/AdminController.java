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
import com.hotelprauriu.app.entities.Question;
import com.hotelprauriu.app.services.QuestionService;
import com.hotelprauriu.app.services.ReservationService;

@Controller
public class AdminController {

    private final ReservationService reservationService;
    private final QuestionService questionService;

    public AdminController(ReservationService reservationService, QuestionService questionService) {
        this.reservationService = reservationService;
        this.questionService = questionService;
    }

    // Full Admin Panel Page
    @RequestMapping(value = { "/admin", "/dashboard" }, method = RequestMethod.GET)
    public String getAdminPanel(
            Model model,
            @Qualifier("reservation") @PageableDefault(page = 0, size = 4) Pageable reservationPageable,
            @Qualifier("question") @PageableDefault(page = 0, size = 4) Pageable questionPageable,
            @RequestParam(value = "reservation_page", defaultValue = "0") int reservationPage,
            @RequestParam(value = "question_page", defaultValue = "0") int questionPage) {

        // Create custom pageable for reservations and questions
        Pageable reservationPaging = PageRequest.of(reservationPage, reservationPageable.getPageSize());
        Pageable questionPaging = PageRequest.of(questionPage, questionPageable.getPageSize());

        // Fetch the paginated data
        Page<Reservation> reservationList = reservationService.findAll(reservationPaging);
        Page<Question> questionList = questionService.findAll(questionPaging);

        // Add data to the model
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("questionList", questionList);

        // Return the full page template
        return "admin/pages/home/dashboard";
    }

    @RequestMapping(value = "/admin/reservations", method = RequestMethod.GET)
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

    @RequestMapping(value = "/admin/questions", method = RequestMethod.GET)
    public String getQuestions(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 4) Pageable pageable) {

        Pageable paging = PageRequest.of(page, pageable.getPageSize());
        Page<Question> questionList = questionService.findAll(paging);

        model.addAttribute("questionList", questionList);

        // Return the Thymeleaf fragment
        return "admin/fragments/tables/questions";
    }

}
