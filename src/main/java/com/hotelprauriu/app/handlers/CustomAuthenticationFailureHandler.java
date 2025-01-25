package com.hotelprauriu.app.handlers;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Clase para manejar el fallo de la autenticación.
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private LoggerService loggerService;

    /**
     * Maneja el fallo de la autenticación.
     * 
     * @param request   la petición HTTP.
     * @param response  la respuesta HTTP.
     * @param exception la excepción de autenticación.
     * @throws IOException si ocurre algún error al manejar el fallo de la
     *                     autenticación.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException {

        String username = request.getParameter("username");

        Log log = new Log();
        log.setAction(Log.Action.LOGIN_ERROR);
        log.setMessage("Login error: " + username + " - " + exception.getMessage());
        log.setDate(new Date());

        loggerService.log(log);

        response.sendRedirect("/login?error");
    }
}