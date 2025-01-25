package com.hotelprauriu.app.interceptors;

import java.util.Date;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hotelprauriu.app.entities.Log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ReservationInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable ModelAndView modelAndView)
            throws Exception {
        String requestUrl = request.getRequestURI();
        if (requestUrl.contains("reservation")) {

            Log log = new Log();
            log.setAction(Log.Action.SIGNUP);
            log.setMessage("Registration " + request.getMethod() + " URL: " + request.getRequestURI());
            log.setDate(new Date());

        }
    }
    
}
