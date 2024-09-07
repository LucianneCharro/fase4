package com.fiap.gerenciamento_encomendas.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MoradoresIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListarMoradores() throws Exception {
        mockMvc.perform(get("/cadastrar-moradores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAdicionarMorador() throws Exception {
        mockMvc.perform(post("/cadastrar-moradores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"telefone\":\"(11) 99611-7525\",\"apartamento\":\"101\",\"nome\":\"Lu\",\"email\":\"xx@xx\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRemoverMorador() throws Exception {
        // Cria um morador para garantir que ele exista
        mockMvc.perform(post("/cadastrar-moradores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"telefone\":\"(11) 99611-7525\",\"apartamento\":\"101\",\"nome\":\"Lu\",\"email\":\"xx@xx\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/cadastrar-moradores/" + 1))
                .andExpect(status().isOk());
    }
}