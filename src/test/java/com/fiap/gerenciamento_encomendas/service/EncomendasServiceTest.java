package com.fiap.gerenciamento_encomendas.service;

import com.fiap.gerenciamento_encomendas.dto.EncomendaDTO;
import com.fiap.gerenciamento_encomendas.model.Encomenda;
import com.fiap.gerenciamento_encomendas.repository.EncomendasRepository;
import com.fiap.gerenciamento_encomendas.repository.NotificacaoRepository;
import com.fiap.gerenciamento_encomendas.service.encomendas.EncomendasService;
import com.fiap.gerenciamento_encomendas.service.notificacao.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EncomendasServiceTest {

    @Mock
    private EncomendasRepository encomendasRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @InjectMocks
    private EncomendasService encomendasService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void adicionarEncomenda_ShouldSaveEncomenda() {
        EncomendaDTO encomendaDTO = new EncomendaDTO();
        encomendaDTO.setNomeMorador("John Doe");
        encomendaDTO.setNumeroApartamento("101");
        encomendaDTO.setDescricao("Package");

        encomendasService.receberEncomendaPortaria(encomendaDTO);

        verify(encomendasRepository, times(1)).save(any(Encomenda.class));
    }

    @Test
    void processarEncomendas_ShouldSendNotificationAndUpdateStatus() {
        EncomendaDTO encomendaDTO = new EncomendaDTO();
        encomendaDTO.setNomeMorador("John Doe");
        encomendaDTO.setNumeroApartamento("101");
        encomendaDTO.setDescricao("Package");

        Encomenda encomenda = new Encomenda();
        encomenda.setId(1L);
        encomenda.setNomeMorador("John Doe");
        encomenda.setNumeroApartamento("101");
        encomenda.setDescricao("Package");
        encomenda.setStatus("Recebida");

        when(encomendasRepository.findByNomeMoradorAndDescricao(anyString(), anyString())).thenReturn(encomenda);

        encomendasService.receberEncomendaPortaria(encomendaDTO);
        encomendasService.processarEncomendasPortaria();

        verify(emailService, times(1)).enviarEmailTexto(anyString(), anyString(), anyString());
        verify(encomendasRepository, times(2)).save(any(Encomenda.class));
    }

    @Test
    void registrarRetirada_ShouldUpdateStatus() {
        Long encomendaId = 1L;
        Encomenda encomenda = new Encomenda();
        encomenda.setId(encomendaId);
    }
}