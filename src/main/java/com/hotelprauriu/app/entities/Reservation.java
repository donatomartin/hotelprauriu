package com.hotelprauriu.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Reservations")
@Getter
@Setter
public class Reservation {

    public enum Status {
        PENDING,
        ACCEPTED,
        REFUSED,
        DISCARDED,
        PAYED
    }

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @NotNull(message = "{error.checkin.date.required}")
    @Future(message = "{error.checkin.date.future}")
    private LocalDate checkIn;

    @NotNull(message = "{error.checkout.date.required}")
    @Future(message = "{error.checkout.date.future}")
    private LocalDate checkOut;

    @NotBlank(message = "{error.guest.name.required}")
    @Size(min = 2, max = 100, message = "{error.guest.name.size}")
    private String guestFullName;

    @NotBlank(message = "{error.guest.email.required}")
    @Email(message = "{error.guest.email.invalid}")
    private String guestEmail;

    @NotBlank(message = "{error.guest.phone.prefix.required}")
    private String guestPhonePrefix;
    
    @NotBlank(message = "{error.guest.phone.number.required}")
    private String guestPhoneNumber;

    public void setGuestPhoneNumber(String guestPhoneNumber) {
        this.guestPhoneNumber = guestPhoneNumber.replace(" ", "");
    }

    @Transient
    private String guestFullPhoneNumber;

    public String getGuestFullPhoneNumber() {
        return guestPhonePrefix + " " + guestPhoneNumber;
    }

    @NotNull(message = "{error.guests.count.required}")
    @Min(value = 1, message = "{error.guests.count.min}")
    @Max(value = 4, message = "{error.guests.count.max}")
    private int numberOfGuests;

    @NotNull(message = "{error.pets.count.required}")
    @Min(value = 0, message = "{error.pets.count.min}")
    @Max(value = 2, message = "{error.pets.count.max}")
    private int numberOfPets;

    @Size(max = 511, message = "{error.guest.message.size}")
    private String guestMessage;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String response;
}
