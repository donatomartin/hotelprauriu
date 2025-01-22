package com.hotelprauriu.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Esta clase proporciona servicios relacionados con la seguridad de la aplicación.
 * Se encarga de la autenticación y autorización de los usuarios.
 */
@Service
public class SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor de la clase SecurityService.
     * 
     * @param authenticationManager El administrador de autenticación utilizado para autenticar a los usuarios.
     * @param userDetailsService El servicio utilizado para cargar los detalles de los usuarios.
     */
    public SecurityService(AuthenticationManager authenticationManager, UserDetailsService
            userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Devuelve el correo electrónico del usuario autenticado actualmente.
     * 
     * @return El correo electrónico del usuario autenticado actualmente, o null si no hay usuario autenticado.
     */
    public String findLoggedInEmail() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    /**
     * Realiza el inicio de sesión automático del usuario con las credenciales proporcionadas.
     * 
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public void autoLogin(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(aToken);
        if (aToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(aToken);
            logger.debug(String.format("Inicio de sesión automático exitoso para %s!", email));
        }
    }
}
