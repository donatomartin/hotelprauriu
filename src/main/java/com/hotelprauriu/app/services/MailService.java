package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.Reservation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  private final AsyncEmailSender asyncEmailSender;

  private String templateReservationClient =
      """
Hola {name} 👋🏻

¡Hemos recibido tu consulta! Nos pondremos en contacto contigo lo antes posible para confirmar.

Saludos,
Prau Ríu Hotel

------------------------
 Detalles de la consulta
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
  private String templateReservationHotel =
      """
      ¡Has recibido una consulta de {name}!
      Revísala en hotelprauriu.com/admin/inbox

      ------------------------
       Detalles de la consulta
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
    this.asyncEmailSender = new AsyncEmailSender(mailSender);
  }

  public void sendMailsAboutReservation(Reservation reservation) {

    // Send email to client

    Map<String, Object> params =
        Map.of(
            "name", reservation.getGuestFullName(),
            "email", reservation.getGuestEmail(),
            "phone", reservation.getGuestFullPhoneNumber(),
            "nguests", reservation.getNumberOfGuests(),
            "npets", reservation.getNumberOfPets(),
            "checkin", reservation.getCheckIn(),
            "checkout", reservation.getCheckOut(),
            "message", reservation.getGuestMessage());

    String clientSubject = "[HotelPrauRiu] Consulta Enviada";
    String clientText = formatWithNamedParameters(templateReservationClient, params);
    asyncEmailSender.sendMail(reservation.getGuestEmail(), clientSubject, clientText);

    // Send email to hotel

    String hotelSubject = "[HotelPrauRiu] Consulta Recibida";
    String hotelText = formatWithNamedParameters(templateReservationHotel, params);
    asyncEmailSender.sendMail(adminEmail, hotelSubject, hotelText);
  }

  private String formatWithNamedParameters(String template, Map<String, Object> params) {
    String result = template;
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      result = result.replace("{" + entry.getKey() + "}", entry.getValue().toString());
    }
    return result;
  }
}
