package com.hotelprauriu.app.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.entities.Question;

import com.hotelprauriu.app.services.QuestionService;
import com.hotelprauriu.app.services.ReservationService;

@Controller
public class AdminController {

    private ReservationService reservationService;
    private QuestionService questionService;

    public AdminController(ReservationService reservationService, QuestionService questionService) {
        this.reservationService = reservationService;
        this.questionService = questionService;
    }

    @RequestMapping(value = { "/admin", "/dashboard" }, method = RequestMethod.GET)
    public String getAdminPanel(
            Model model,
            @Qualifier("reservation") Pageable reservationPageable,
            @Qualifier("question") Pageable questionPageable) {

        Page<Reservation> reservationList = reservationService.findAll(reservationPageable);
        model.addAttribute("reservationList", reservationList);

        Page<Question> questionList = questionService.findAll(questionPageable);
        model.addAttribute("questionList", questionList);

        return "admin/pages/home/dashboard";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String getLogin() {
        return "admin/pages/auth/login";
    }

}
