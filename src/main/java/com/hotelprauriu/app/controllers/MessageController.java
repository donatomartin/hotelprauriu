package com.hotelprauriu.app.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelprauriu.app.entities.Message;
import com.hotelprauriu.app.services.MailService;
import com.hotelprauriu.app.services.MessageService;

@Controller
public class MessageController {

    private final MessageService messageService;
    private final MailService mailService;

    public MessageController(

            MessageService messageService,
            MailService mailService

    ) {

        this.messageService = messageService;
        this.mailService = mailService;

    }

    @RequestMapping(value = { "/message" }, method = RequestMethod.POST)
    public String postMessage(@Validated Message message) {

        mailService.sendMailsAboutMessage(message);
        messageService.addMessage(message);

        return "guest/pages/home/index";
    }

    @RequestMapping(value = "/admin/partial/messages", method = RequestMethod.GET)
    public String getmessages(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(size = 2) Pageable pageable) {

        Pageable paging = PageRequest.of(page, pageable.getPageSize());
        Page<Message> messageList = messageService.findAll(paging);

        model.addAttribute("messageList", messageList);

        // Return the Thymeleaf fragment
        return "admin/fragments/tables/messages";
    }

}
