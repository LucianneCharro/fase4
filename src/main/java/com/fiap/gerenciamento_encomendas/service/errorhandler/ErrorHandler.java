package com.fiap.gerenciamento_encomendas.service.errorhandler;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler {

    @ServiceActivator(inputChannel = "errorChannel")
    public void handleError(Message<?> message) {
        System.out.println("Error occurred: " + message);
    }
}