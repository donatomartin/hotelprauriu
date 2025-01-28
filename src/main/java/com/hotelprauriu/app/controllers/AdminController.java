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

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.services.LoggerService;

@Controller
public class AdminController {

    private final LoggerService loggerService;

    public AdminController(
            LoggerService loggerService) {

        this.loggerService = loggerService;

    }

    // Full Admin Panel Page
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String getAdminPanel() {
        return "admin/pages/home/dashboard";
    }

    @RequestMapping(value = "/admin/inbox", method = RequestMethod.GET)
    public String getInbox() {
        return "admin/pages/home/inbox";
    }

    @RequestMapping("/admin/logs")
    public String getLogs(

            Model model,
            @Qualifier("logs") @PageableDefault(page = 0, size = 12) Pageable pageable,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Integer page

    ) {

        if (page == null)
            page = 0;

        Pageable logsPageable = PageRequest.of(page, pageable.getPageSize());

        Page<Log> logPage;

        // Si no hay ningún filtro
        if (action != null && !action.isEmpty() && !action.equals("ALL")) {
            logPage = loggerService.findLogsByAction(action, logsPageable);
        } else {
            action = "ALL";
            logPage = loggerService.getLogs(logsPageable);
        }

        model.addAttribute("action", action);
        model.addAttribute("page", logPage);

        return "admin/pages/home/logs";
    }

    @RequestMapping("/admin/logs/delete")
    public String deleteLogs(Model model, @RequestParam(required = false) String action) {
        // Si no hay ningún filtro
        if (action != null && !action.isEmpty() && !action.equals("ALL")) {
            loggerService.deleteLogs(action);
        } else {
            loggerService.deleteLogs();
        }

        return "redirect:/admin/logs";
    }

    @RequestMapping("/admin/templates")
    public String getTemplates() {
        return "admin/pages/home/templates";
    }

    @RequestMapping("/admin/settings")
    public String getSettings() {
        return "admin/pages/home/settings";
    }

    @RequestMapping("/admin/users")
    public String getUsers() {
        return "admin/pages/home/users";
    }

}
