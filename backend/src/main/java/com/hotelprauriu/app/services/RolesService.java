package com.hotelprauriu.app.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {

    public String[] getRoles() {
        return new String[] { "ROLE_ADMIN", "ROLE_USER", "ROLE_EMPLOYEE" };
    }
    
}
