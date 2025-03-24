package com.hotelprauriu.app.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/** Clase para manejar el fallo de la autenticación. */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  /**
   * Maneja el fallo de la autenticación.
   *
   * @param request la petición HTTP.
   * @param response la respuesta HTTP.
   * @param exception la excepción de autenticación.
   * @throws IOException si ocurre algún error al manejar el fallo de la autenticación.
   */
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {

    String username = request.getParameter("username");

    response.sendRedirect("/login?error");
  }
}

