package com.hotelprauriu.app.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();

        String htmlContent = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f9f9f9;
                            margin: 0;
                            padding: 0;
                        }
                        .email-container {
                            max-width: 600px;
                            margin: 20px auto;
                            background: #ffffff;
                            border: 1px solid #dddddd;
                            padding: 20px;
                            border-radius: 8px;
                        }
                        h1 {
                            color: #333333;
                        }
                        p {
                            color: #555555;
                        }
                        a {
                            color: #1a73e8;
                            text-decoration: none;
                        }
                        .button {
                            display: inline-block;
                            margin-top: 20px;
                            background-color: #1a73e8;
                            color: #ffffff;
                            padding: 10px 20px;
                            text-align: center;
                            text-decoration: none;
                            border-radius: 5px;
                        }
                        </style>
                    </head>
                    <body>
                        <div class="email-container">
                        <h1>¡Hola, %s!</h1>
                        <p>Gracias por hacer una reserva en nuestro portal. Aquí tienes los detalles:</p>
                        <ul>
                            <li><strong>Fecha:</strong> 22 de enero, 2025</li>
                            <li><strong>Hora:</strong> 18:00</li>
                            <li><strong>Ubicación:</strong> Restaurante Ejemplo</li>
                        </ul>
                        <p>Para más detalles, visita nuestro sitio web.</p>
                        <a href="https://tuportal.com" class="button">Ver mi reserva</a>
                        </div>
                    </body>
                </html>""";

        String.format(htmlContent, name);

        message.setTo(to);
        message.setSubject("Texto");
        message.setText(htmlContent);

        try {
            mailSender.send(message);
            System.out.println("Correo enviado correctamente a: " + to);
        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
        }
    }
}
