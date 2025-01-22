package com.hotelprauriu.app.interceptors;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.services.LoggerService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerService loggerService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) {

        Log log = new Log();
        log.setAction(Log.Action.PET);
        log.setMessage("Request " + request.getMethod() + " URL:  " + request.getRequestURI());
        log.setDate(new Date());

        loggerService.log(log);
        return true;
    }

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable ModelAndView modelAndView)
            throws Exception {
        String requestUrl = request.getRequestURI();
        if (requestUrl.contains("signup")) {

            Log log = new Log();
            log.setAction(Log.Action.SIGNUP);
            log.setMessage("Registration " + request.getMethod() + " URL: " + request.getRequestURI());
            log.setDate(new Date());

            loggerService.log(log);
        }
    }

}