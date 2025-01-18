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

    private ReservationService reservationService;
    private QuestionService questionService;

    public AdminController(ReservationService reservationService, QuestionService questionService) {
        this.reservationService = reservationService;
        this.questionService = questionService;
    }

    @RequestMapping(value = { "/admin", "/dashboard" }, method = RequestMethod.GET)
    public String getAdminPanel(
            Model model,
            @Qualifier("reservation") @PageableDefault(page = 0, size = 2) Pageable reservationPageable,
            @Qualifier("question") @PageableDefault(page = 0, size = 2) Pageable questionPageable,
            @RequestParam(value = "reservation_page", defaultValue = "0") int reservationPage,
            @RequestParam(value = "question_page", defaultValue = "0") int questionPage) {

        // Crear objetos Pageable manualmente para cada tabla
        Pageable reservationPaging = PageRequest.of(reservationPage, reservationPageable.getPageSize());
        Pageable questionPaging = PageRequest.of(questionPage, questionPageable.getPageSize());

        // Obtener las páginas
        Page<Reservation> reservationList = reservationService.findAll(reservationPaging);
        Page<Question> questionList = questionService.findAll(questionPaging);

        // Pasar los resultados al modelo
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("questionList", questionList);

        return "admin/pages/home/dashboard";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String getLogin() {
        return "admin/pages/auth/login";
    }

}
