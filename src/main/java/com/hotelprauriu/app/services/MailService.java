package com.hotelprauriu.app.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hotelprauriu.app.entities.Message;
import com.hotelprauriu.app.entities.Reservation;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final String hotelMail = "vampirovegano@gmail.com";

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMailsAboutMessage(Message message) {
        
        // Send email to client
        
        String clientSubject = "[HotelPrauRiu] Mensaje Enviado";
        String clientText = "Hola %s 👋🏻\n¡hemos recibido tu mensaje! Nos pondremos en contacto contigo lo antes posible.\n\nSaludos,\nHotel Prau Riu\n\nMensaje recibido:\n%s";
        clientText = String.format(clientText, message.getGuestFullName(), message.getGuestMessage());

        sendMail(message.getGuestEmail(), clientSubject, clientText);

        // Send email to hotel

        String hotelSubject = "[HotelPrauRiu] Mensaje Recibido";
        String hotelText = "Has recibido un nuevo mensaje de %s 👋🏻\n\nMensaje:\n%s";
        hotelText = String.format(hotelText, message.getGuestFullName(), message.getGuestMessage());

        sendMail(hotelMail, hotelSubject, hotelText);

    }

    public void sendMailsAboutReservation(Reservation reservation) {
        
        // Send email to client

        String clientSubject = "[HotelPrauRiu] Reserva Enviada";
        String clientText = "Hola %s 👋🏻\n¡hemos recibido tu reserva! Nos pondremos en contacto contigo lo antes posible.\n\nSaludos,\nHotel Prau Riu\n\nMensaje recibido:\n%s";
        clientText = String.format(clientText, reservation.getGuestFullName(), reservation.getGuestMessage());

        sendMail(reservation.getGuestEmail(), clientSubject, clientText);
        
        // Send email to hotel

        String hotelSubject = "[HotelPrauRiu] Reserva Recibida";
        String hotelText = "Has recibido una nueva reserva de %s 👋🏻\n\nMensaje:\n%s";
        
        sendMail(hotelMail, hotelSubject, hotelText);
        
    }

    private void sendMail(String to, String subject, String text) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);

        try {
            mailSender.send(mail);
            System.out.println("Correo enviado correctamente a: " + mail.getTo());
        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
        }

    }

}
