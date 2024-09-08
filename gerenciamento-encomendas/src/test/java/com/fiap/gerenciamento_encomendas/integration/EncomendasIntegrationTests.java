package com.fiap.gerenciamento_encomendas.integration;

import com.fiap.gerenciamento_encomendas.service.notificacao.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class EncomendasIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Test
    public void testReceberEncomenda() throws Exception {
        when(emailService.enviarEmailTexto("Teste", "Descricao da encomenda", "xx@xx")).thenReturn("true");
        mockMvc.perform(post("/encomendas-portaria/receber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Teste\",\"descricao\":\"Descricao da encomenda\",\"moradorId\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testProcessarEncomendas() throws Exception {
        mockMvc.perform(get("/encomendas-portaria/processar"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegistrarRetirada() throws Exception {
        mockMvc.perform(post("/encomendas-portaria/retirar-morador/1"))
                .andExpect(status().isOk());
    }
}