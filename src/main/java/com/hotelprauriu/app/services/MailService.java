package com.hotelprauriu.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Reservation;

import java.util.Map;


@Service
public class MailService {

    private final JavaMailSender mailSender;

    private String templateReservationClient = """
            Hola {name} 👋🏻

            ¡Hemos recibido tu consulta! Nos pondremos en contacto contigo lo antes posible para confirmar.

            Saludos,
            Prau Ríu Hotel

            ------------------------
             Detalles de la reserva
            ------------------------

            Checkin: {checkin}
            Checkout: {checkout}

            Número de huéspedes: {nguests}
            Número de mascotas: {npets}

            correo: {email}
            teléfono: {phone}

            Mensaje:
            {message}

            """;
    private String templateReservationHotel = """
            ¡Has recibido una consulta de {name}!
            Revísala en hotelprauriu.com/admin/inbox

            ------------------------
             Detalles de la reserva
            ------------------------

            Checkin: {checkin}
            Checkout: {checkout}

            Número de huéspedes: {nguests}
            Número de mascotas: {npets}

            correo: {email}
            teléfono: {phone}

            Mensaje:
            {message}

            """;

    @Value("${hotelprauriu.admin.email}")
    private String adminEmail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMailsAboutReservation(Reservation reservation) {
        
        // Send email to client

        Map<String, Object> params = Map.of(
            "name", reservation.getGuestFullName(),
            "email", reservation.getGuestEmail(),
            "phone", reservation.getGuestFullPhoneNumber(),
            "nguests", reservation.getNumberOfGuests(),
            "npets", reservation.getNumberOfPets(),
            "checkin", reservation.getCheckIn(),
            "checkout", reservation.getCheckOut(),
            "message", reservation.getGuestMessage()
        );

        String clientSubject = "[HotelPrauRiu] Consulta Enviada";
        String clientText = formatWithNamedParameters(templateReservationClient, params);
        sendMail(reservation.getGuestEmail(), clientSubject, clientText);
        
        // Send email to hotel

        String hotelSubject = "[HotelPrauRiu] Consulta Recibida";
        String hotelText = formatWithNamedParameters(templateReservationHotel, params);
        sendMail(adminEmail, hotelSubject, hotelText);
        
    }

    private void sendMail(String to, String subject, String text) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);

        try {
            mailSender.send(mail);
            System.out.println("Correo enviado correctamente a: " + to);
        } catch (Exception exception) {
            System.err.println("Error al enviar correo: " + exception.getMessage());
        }

    }

    private String formatWithNamedParameters(String template, Map<String, Object> params) {
        String result = template;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return result;
    }

}
