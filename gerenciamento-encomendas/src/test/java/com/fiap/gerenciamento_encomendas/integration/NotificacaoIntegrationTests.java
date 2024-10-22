package com.fiap.gerenciamento_encomendas.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class NotificacaoIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReceberEncomenda() throws Exception {
        mockMvc.perform(post("/encomendas-portaria/receber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Teste\",\"descricao\":\"Descricao da encomenda\",\"moradorId\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegistrarRetirada() throws Exception {
        mockMvc.perform(post("/encomendas-portaria/retirar-morador/1"))
                .andExpect(status().isOk());
    }
}