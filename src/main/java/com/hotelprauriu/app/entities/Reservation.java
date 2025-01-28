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

    @NotNull(message = "Check-in date is required")
    @Future(message = "Check-in date must be in the future")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOut;

    @NotBlank(message = "Guest name is required")
    @Size(min = 2, max = 100, message = "Guest name must be between 2 and 100 characters")
    private String guestFullName;

    @NotBlank(message = "Guest email is required")
    @Email(message = "Please provide a valid email address")
    private String guestEmail;

    @NotBlank(message = "Guest phone prefix is required")
    private String guestPhonePrefix;
    
    @NotBlank(message = "Guest phone number is required")
    private String guestPhoneNumber;

    public void setGuestPhoneNumber(String guestPhoneNumber) {
        this.guestPhoneNumber = guestPhoneNumber.replace(" ", "");
    }

    @Transient
    private String guestFullPhoneNumber;

    public String getGuestFullPhoneNumber() {
        return guestPhonePrefix + " " + guestPhoneNumber;
    }

    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "At least 1 guest is required")
    @Max(value = 4, message = "Maximum 4 guests allowed")
    private int numberOfGuests;

    @NotNull(message = "Number of pets is required")
    @Min(value = 0, message = "Number of pets cannot be negative")
    @Max(value = 2, message = "Maximum 2 pets allowed")
    private int numberOfPets;

    @Size(max = 511, message = "Message must be under 511 characters")
    private String guestMessage;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "Response is required")
    private String response;

}