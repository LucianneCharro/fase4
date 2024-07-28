package com.fiap.gerenciamento_encomendas;

import com.fiap.gerenciamento_encomendas.config.SwaggerConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {SwaggerConfigTest.class})
class GerenciamentoEncomendasApplicationTests {

	@Test
	void contextLoads() {
	}

}
