package com.hotelprauriu.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import com.hotelprauriu.app.handlers.CustomAccessDeniedHandler;
import com.hotelprauriu.app.handlers.CustomAuthenticationFailureHandler;
import com.hotelprauriu.app.handlers.CustomAuthenticationSuccessHandler;
import com.hotelprauriu.app.handlers.CustomLogoutHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomLogoutHandler logoutHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    // Authentication manager for Spring Security.
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Password encoder for Spring Security.
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Thymeleaf dialect for Spring Security.
    @Bean
    SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .permitAll()
        )

        .logout(logout -> logout
            .addLogoutHandler(logoutHandler)
            .permitAll()
        )

        .exceptionHandling(handling -> handling
            .accessDeniedHandler(accessDeniedHandler)
        )
        ;


        return http.build();
    }
}
