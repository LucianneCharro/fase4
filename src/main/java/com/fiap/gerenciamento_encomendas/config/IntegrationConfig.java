package com.fiap.gerenciamento_encomendas.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel errorChannel() {
        return new PublishSubscribeChannel();
    }
    @Bean
    public MessageChannel encomendasChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel notificacoesChannel() {
        return new DirectChannel();
    }
}