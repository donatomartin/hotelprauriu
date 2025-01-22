package com.hotelprauriu.app.handlers;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * Clase para manejar el éxito de la autenticación.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoggerService loggerService;

    /**
     * Maneja el éxito de la autenticación.
     * 
     * @param request        la petición HTTP.
     * @param response       la respuesta HTTP.
     * @param authentication la autenticación.
     * @throws IOException si ocurre algún error al manejar el éxito de la
     *                     autenticación.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {

        Log log = new Log();
        log.setAction(Log.Action.LOGIN_EX);
        log.setMessage("Login success: " + authentication.getName());
        log.setDate(new Date());

        loggerService.log(log);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String path = "";

        // Comprueba su rol y redirige a la página correspondiente
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                path = "/admin/dashboard";
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                path = "/user/dashboard";
            } else {
                throw new IllegalStateException();
            }
        }

        try {
            response.sendRedirect(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}