package com.hotelprauriu.app.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncEmailSender {

  private final JavaMailSender mailSender;

  public AsyncEmailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async
  public void sendMail(String to, String subject, String text) {
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
}
