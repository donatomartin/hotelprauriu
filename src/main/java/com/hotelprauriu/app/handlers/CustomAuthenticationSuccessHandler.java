package com.hotelprauriu.app.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/** Clase para manejar el éxito de la autenticación. */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  /**
   * Maneja el éxito de la autenticación.
   *
   * @param request la petición HTTP.
   * @param response la respuesta HTTP.
   * @param authentication la autenticación.
   * @throws IOException si ocurre algún error al manejar el éxito de la autenticación.
   */
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    String path = "";

    // Comprueba su rol y redirige a la página correspondiente
    for (GrantedAuthority authority : authorities) {
      if (authority.getAuthority().equals("ROLE_ADMIN")) {
        path = "/admin";
      } else if (authority.getAuthority().equals("ROLE_USER")) {
        path = "/user";
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

