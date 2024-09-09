package com.fiap.gerenciamento_encomendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GerenciamentoEncomendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoEncomendasApplication.class, args);
	}

}
