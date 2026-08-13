package com.nova.talentnova.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendActivationEmail(String to, String companyName, String code) {
        try {
            // 1. Creamos el contexto de Thymeleaf y pasamos las variables del HTML
            Context context = new Context();
            context.setVariable("companyName", companyName);
            context.setVariable("code", code);

            // 2. Procesamos el archivo "email-activation.html" ubicado en resources/templates/
            String htmlContent = templateEngine.process("email-activation", context);

            // 3. Creamos el mensaje MIME para soportar HTML y UTF-8
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom("yire2005lol@gmail.com"); // Debe coincidir con tu username del properties
            helper.setTo(to);
            helper.setSubject("Código de Verificación - NOVA");
            helper.setText(htmlContent, true); // El 'true' indica que el contenido es HTML

            // 4. Enviamos el correo
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico de activación", e);
        }
    }
}