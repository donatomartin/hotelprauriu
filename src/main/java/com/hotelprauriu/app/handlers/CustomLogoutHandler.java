package com.hotelprauriu.app.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/** Clase que maneja el logout de la aplicación. */
@Component
public class CustomLogoutHandler implements LogoutHandler {

  /**
   * Método que se ejecuta cuando se realiza el logout.
   *
   * @param request la solicitud HTTP recibida
   * @param response la respuesta HTTP que se enviará
   * @param authentication la autenticación del usuario
   */
  @Override
  public void logout(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    try {
      response.sendRedirect("/login?logout");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

