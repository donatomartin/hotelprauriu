package com.hotelprauriu.app.services.devservices;

import java.time.LocalDate;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.services.ReservationService;
import com.hotelprauriu.app.services.RolesService;
import com.hotelprauriu.app.services.UserService;

import jakarta.annotation.PostConstruct;

@Service
@Profile("dev")
public class SampleDataInsertionService {

    public UserService userService;
    public ReservationService reservationService;
    public RolesService rolesService;

    public SampleDataInsertionService(
        UserService userService,
        ReservationService reservationService,
        RolesService rolesService
    ) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.rolesService = rolesService;
    }

    @PostConstruct
    public void init() {

        System.out.println("SampleDataInsertionService: Inserting Data");

        userService.deleteAll();
        reservationService.deleteAll();

        userService.ensureAdminExists();

        // Reservations

        Reservation reservation1 = new Reservation();
        reservation1.setGuestFullName("John Doe");
        reservation1.setGuestEmail("john@example.com");
        reservation1.setGuestPhonePrefix("+34");
        reservation1.setGuestPhoneNumber("1234567890"); 
        reservation1.setNumberOfGuests(1);
        reservation1.setNumberOfPets(2);
        reservation1.setGuestMessage("La cama sin chinches por favor");
        reservation1.setCheckIn(LocalDate.now().plusDays(1));
        reservation1.setCheckOut(LocalDate.now().plusDays(3));
        reservation1.setTermsAccepted(true);
        
        reservationService.addReservation(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setGuestFullName("Jane Smith");
        reservation2.setGuestEmail("jane@example.com");
        reservation2.setGuestPhonePrefix("+34");
        reservation2.setGuestPhoneNumber("0987654321");
        reservation2.setNumberOfGuests(2);
        reservation2.setNumberOfPets(2);
        reservation2.setGuestMessage("Would like a room with a view");
        reservation2.setCheckIn(LocalDate.now().plusDays(1));
        reservation2.setCheckOut(LocalDate.now().plusDays(3));
        reservation2.setTermsAccepted(true);

        reservationService.addReservation(reservation2);

        Reservation reservation3 = new Reservation();
        reservation3.setGuestFullName("Bob Wilson");
        reservation3.setGuestEmail("bob@example.com");
        reservation3.setGuestPhonePrefix("+34");
        reservation3.setGuestPhoneNumber("5555 555555");
        reservation3.setNumberOfGuests(1);
        reservation3.setNumberOfPets(2);
        reservation3.setGuestMessage("La cama sin chinches por favor");
        reservation3.setCheckIn(LocalDate.now().plusDays(1));
        reservation3.setCheckOut(LocalDate.now().plusDays(3));
        reservation3.setTermsAccepted(true);

        reservationService.addReservation(reservation3);

        Reservation reservation4 = new Reservation();
        reservation4.setGuestFullName("Donato Martín");
        reservation4.setGuestEmail("donato@example.com");
        reservation4.setGuestPhonePrefix("+34");
        reservation4.setGuestPhoneNumber("555 55555 55");
        reservation4.setNumberOfGuests(1);
        reservation4.setNumberOfPets(0);
        reservation4.setCheckIn(LocalDate.now().plusDays(1));
        reservation4.setCheckOut(LocalDate.now().plusDays(3));
        reservation4.setTermsAccepted(true);

        reservationService.addReservation(reservation4);

        Reservation reservation5 = new Reservation();
        reservation5.setGuestFullName("Carlos Rodriguez");
        reservation5.setGuestEmail("carlos@example.com");
        reservation5.setGuestPhonePrefix("+34");
        reservation5.setGuestPhoneNumber("55 555 55555");
        reservation5.setNumberOfGuests(1);
        reservation5.setNumberOfPets(2);
        reservation5.setGuestMessage("La cama sin chinches por favor");
        reservation5.setCheckIn(LocalDate.now().plusDays(1));
        reservation5.setCheckOut(LocalDate.now().plusDays(3));
        reservation5.setTermsAccepted(true);

        reservationService.addReservation(reservation5);

    }


    
}
