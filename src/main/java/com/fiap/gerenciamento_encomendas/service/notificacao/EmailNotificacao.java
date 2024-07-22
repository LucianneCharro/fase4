package com.fiap.gerenciamento_encomendas.service.notificacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailNotificacao implements NotificacaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificacao.class);
    private JavaMailSender mailSender;

    @Autowired
    public EmailNotificacao(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarNotificacao(String destinatario, String mensagem) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(destinatario);
            email.setSubject("Notificação de Encomenda");
            email.setText(mensagem);
            mailSender.send(email);
            logger.info("Notificação enviada para {}: {}", destinatario, mensagem);
        } catch (Exception e) {
            logger.error("Falha ao enviar notificação para {}: {}", destinatario, e.getMessage());
        }
    }
}