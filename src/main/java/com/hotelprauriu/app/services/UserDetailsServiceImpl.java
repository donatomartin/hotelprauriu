package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.User;
import com.hotelprauriu.app.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación de la interfaz UserDetailsService que se utiliza para cargar los detalles de un usuario
 * durante la autenticación.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository usersRepository;

    /**
     * Constructor de la clase UserDetailsServiceImpl.
     *
     * @param usersRepository Repositorio de usuarios utilizado para buscar un usuario por su email.
     */
    public UserDetailsServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Carga los detalles de un usuario por su email.
     *
     * @param username Email del usuario.
     * @return UserDetails con los detalles del usuario.
     * @throws UsernameNotFoundException Si no se encuentra ningún usuario con el email proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(grantedAuthorities.isEmpty());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities
        );
    }
}
