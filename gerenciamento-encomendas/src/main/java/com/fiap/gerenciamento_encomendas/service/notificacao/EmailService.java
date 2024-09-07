package com.fiap.gerenciamento_encomendas.service.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    public String enviarEmailTexto(String destinatario, String assunto, String mensagem) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            emailSender.send(simpleMailMessage);
            return "Email enviado com sucesso!";
        }  catch (Exception e) {
            return "Erro ao enviar email: " + e.getLocalizedMessage();
        }
    }
}