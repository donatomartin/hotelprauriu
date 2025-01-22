package com.hotelprauriu.app.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Message;
import com.hotelprauriu.app.entities.Reservation;
import com.hotelprauriu.app.entities.User;

import jakarta.annotation.PostConstruct;

@Service
public class SampleDataService {

    public UserService userService;
    public ReservationService reservationService;
    public MessageService messageService;
    public RolesService rolesService;

    public SampleDataService(
        UserService userService,
        ReservationService reservationService,
        MessageService messageService,
        RolesService rolesService
    ) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.messageService = messageService;
        this.rolesService = rolesService;
    }

    @PostConstruct
    public void init() {

        userService.deleteAll();
        reservationService.deleteAll();
        messageService.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@admin.com");
        admin.setPassword("admin");
        admin.setRole(rolesService.getRoles()[0]);

        userService.addUser(admin);

        // Reservations

        Reservation reservation1 = new Reservation();
        reservation1.setGuestFullName("John Doe");
        reservation1.setGuestEmail("john@example.com");
        reservation1.setGuestPhoneNumber("1234567890"); 
        reservation1.setNumberOfGuests(1);
        reservation1.setNumberOfDogs(2);
        reservation1.setGuestMessage("La cama sin chinches por favor");
        reservation1.setCheckIn(LocalDate.now().plusDays(1));
        reservation1.setCheckOut(LocalDate.now().plusDays(3));
        
        reservationService.addReservation(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setGuestFullName("Jane Smith");
        reservation2.setGuestEmail("jane@example.com");
        reservation2.setGuestPhoneNumber("0987654321");
        reservation2.setNumberOfGuests(2);
        reservation2.setNumberOfDogs(2);
        reservation2.setGuestMessage("Would like a room with a view");
        reservation2.setCheckIn(LocalDate.now().plusDays(1));
        reservation2.setCheckOut(LocalDate.now().plusDays(3));

        reservationService.addReservation(reservation2);

        Reservation reservation3 = new Reservation();
        reservation3.setGuestFullName("Bob Wilson");
        reservation3.setGuestEmail("bob@example.com");
        reservation3.setGuestPhoneNumber("5555555555");
        reservation3.setNumberOfGuests(1);
        reservation3.setNumberOfDogs(2);
        reservation3.setGuestMessage("La cama sin chinches por favor");
        reservation3.setCheckIn(LocalDate.now().plusDays(1));
        reservation3.setCheckOut(LocalDate.now().plusDays(3));

        reservationService.addReservation(reservation3);

        Reservation reservation4 = new Reservation();
        reservation4.setGuestFullName("Donato Martín");
        reservation4.setGuestEmail("donato@example.com");
        reservation4.setGuestPhoneNumber("5555555555");
        reservation4.setNumberOfGuests(1);
        reservation4.setNumberOfDogs(0);
        reservation4.setCheckIn(LocalDate.now().plusDays(1));
        reservation4.setCheckOut(LocalDate.now().plusDays(3));

        reservationService.addReservation(reservation4);

        Reservation reservation5 = new Reservation();
        reservation5.setGuestFullName("Carlos Rodriguez");
        reservation5.setGuestEmail("carlos@example.com");
        reservation5.setGuestPhoneNumber("5555555555");
        reservation5.setNumberOfGuests(1);
        reservation5.setNumberOfDogs(2);
        reservation5.setGuestMessage("La cama sin chinches por favor");
        reservation5.setCheckIn(LocalDate.now().plusDays(1));
        reservation5.setCheckOut(LocalDate.now().plusDays(3));

        reservationService.addReservation(reservation5);

        // Messages

        Message message1 = new Message();
        message1.setGuestFullName("Alice Johnson");
        message1.setGuestEmail("alice@example.com");
        message1.setGuestMessage("Do you have parking available?");

        messageService.addMessage(message1);

        Message message2 = new Message();
        message2.setGuestFullName("Carlos Rodriguez");
        message2.setGuestEmail("carlos@example.com");
        message2.setGuestMessage("What time is check-in?");
        
        messageService.addMessage(message2);

        Message message3 = new Message();
        message3.setGuestFullName("Maria Garcia");
        message3.setGuestEmail("maria@example.com");
        message3.setGuestMessage("Are pets allowed in all rooms?");
        
        messageService.addMessage(message3);

        Message message4 = new Message();
        
        message4.setGuestFullName("Carlos Rodriguez");
        message4.setGuestEmail("carlos@example.com");
        message4.setGuestMessage("What time is check-in?");

        messageService.addMessage(message4);


        System.out.println("All RESERVATIONS:");
        for (Reservation reservation : reservationService.findAll()) {
            System.out.println(reservation.getGuestFullName());
        }

        System.out.println("All USERS:");
        for (User user : userService.findAll()) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        }

    }


    
}
