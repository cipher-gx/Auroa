package com.ciphergx.auroa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value ("${spring.mail.username}")
    private String remitente;

    @Async
    public void enviarCorreoRecuperacion(String destinatario, String token) {
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setFrom(remitente);
    mensaje.setTo(destinatario);
    mensaje.setSubject("Auroa - Código de recuperación de contraseña");
    mensaje.setText("Hola,\n\nTu código para restablecer tu contraseña es:\n\n" 
                    + token 
                    + "\n\nEste código expirará en 15 minutos.");

    javaMailSender.send(mensaje);
}
}
