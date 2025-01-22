package com.hotelprauriu.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Reservations")
@Getter @Setter
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Check-in date is required")
    @Future(message = "Check-in date must be in the future")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOut;

    @NotBlank(message = "Guest name is required")
    @Size(min = 2, max = 100, message = "Guest name must be between 2 and 100 characters")
    private String guestFullName;

    @Email(message = "Please provide a valid email address")
    private String guestEmail;

    @Transient
    private String guestPhonePrefix;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Please provide a valid phone number")
    private String guestPhoneNumber;

    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "At least 1 guest is required")
    @Max(value = 4, message = "Maximum 4 guests allowed")
    private int numberOfGuests;

    @NotNull(message = "Number of dogs is required")
    @Min(value = 0, message = "Number of dogs cannot be negative")
    @Max(value = 2, message = "Maximum 2 dogs allowed")
    private int numberOfDogs;

    @Size( max=511, message= "Message must be under 511 characters")
    private String guestMessage;

}