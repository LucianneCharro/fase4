package com.fiap.gerenciamento_encomendas.application;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gerenciamento de Encomendas API")
                        .description("API para gerenciamento de encomendas")
                        .contact(new Contact()
                                .name("FIAP")
                                .email("lucianne_ballico@hotmail.com"))
                        .version("1.0"));
    }
}