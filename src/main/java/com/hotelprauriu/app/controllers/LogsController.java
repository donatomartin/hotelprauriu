package com.hotelprauriu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.services.LoggerService;

@Controller
public class LogsController {

    private final LoggerService loggerService;

    public LogsController(
        LoggerService loggerService) {
    
        this.loggerService = loggerService;

    }

    @GetMapping("/admin/logs")
    public String getLogs(

            Model model,
            @Qualifier("logs") @PageableDefault(page = 0, size = 6) Pageable pageable,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer page

    ) {

        if (page == null)
            page = 0;

        Pageable logsPageable = PageRequest.of(page, pageable.getPageSize());

        Page<Log> logPage;

        // Si no hay ningún filtro
        if (filter != null && !filter.isEmpty() && !filter.equals("ALL")) {
            logPage = loggerService.findLogsByAction(filter, logsPageable);
        } else {
            filter = "ALL";
            logPage = loggerService.getLogs(logsPageable);
        }

        model.addAttribute("filter", filter);
        model.addAttribute("page", logPage);

        return "admin/pages/home/logs";
    }

    @GetMapping("/admin/logs/delete")
    public String deleteLogs(Model model, @RequestParam(required = false) String action) {
        // Si no hay ningún filtro
        if (action != null && !action.isEmpty() && !action.equals("ALL")) {
            loggerService.deleteLogs(action);
        } else {
            loggerService.deleteLogs();
        }

        return "redirect:/admin/logs";
    }

}
