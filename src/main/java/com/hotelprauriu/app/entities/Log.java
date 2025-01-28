package com.hotelprauriu.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
public class Log {

    public enum Action {
        PET,
        SIGNUP,
        LOGIN_SUCCESS,
        LOGIN_ERROR,
        LOGOUT
    }

    @Id
    @UuidGenerator
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private Action action;

    @NotBlank
    private String message;

}
