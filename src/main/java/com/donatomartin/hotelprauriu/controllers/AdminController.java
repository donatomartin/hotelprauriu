package com.donatomartin.hotelprauriu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.donatomartin.hotelprauriu.entities.Reservation;
import com.donatomartin.hotelprauriu.entities.Question;

import com.donatomartin.hotelprauriu.services.QuestionService;
import com.donatomartin.hotelprauriu.services.ReservationService;


@Controller
public class AdminController {

    private ReservationService reservationService;
    private QuestionService questionService;

    public AdminController(ReservationService reservationService, QuestionService questionService) {
        this.reservationService = reservationService;
        this.questionService = questionService;
    }
    
    @RequestMapping(value={"/admin", "/dashboard"}, method=RequestMethod.GET)
    public String getAdminPanel(Model model) {

        List<Reservation> reservationList = reservationService.findAll();
        model.addAttribute("reservationList", reservationList);

        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionList", questionList);

        return "admin/pages/home/dashboard";
    }

}
