package com.hotelprauriu.app.handlers;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Clase que maneja el logout de la aplicación.
 */
@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private LoggerService loggerService;

    /**
     * Método que se ejecuta cuando se realiza el logout.
     *
     * @param request        la solicitud HTTP recibida
     * @param response       la respuesta HTTP que se enviará
     * @param authentication la autenticación del usuario
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        Log log = new Log();
        log.setAction(Log.Action.LOGOUT);
        log.setMessage("Logout success: " + authentication.getName());
        log.setDate(new Date());

        loggerService.log(log);

        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}