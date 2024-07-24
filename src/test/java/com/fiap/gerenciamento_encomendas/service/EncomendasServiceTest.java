package com.fiap.gerenciamento_encomendas.service;

import com.fiap.gerenciamento_encomendas.dto.EncomendaDTO;
import com.fiap.gerenciamento_encomendas.model.Encomenda;
import com.fiap.gerenciamento_encomendas.repository.EncomendasRepository;
import com.fiap.gerenciamento_encomendas.repository.NotificacaoRepository;
import com.fiap.gerenciamento_encomendas.service.encomendas.EncomendasService;
import com.fiap.gerenciamento_encomendas.service.notificacao.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EncomendasServiceTest {

    @Mock
    private EncomendasRepository encomendasRepository;

    @Mock
    private NotificacaoService notificacaoService;

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

        encomendasService.adicionarEncomenda(encomendaDTO);

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

        encomendasService.adicionarEncomenda(encomendaDTO);
        encomendasService.processarEncomendas();

        verify(notificacaoService, times(1)).enviarNotificacao(anyString(), anyString());
        verify(encomendasRepository, times(2)).save(any(Encomenda.class));
    }

    @Test
    void registrarRetirada_ShouldUpdateStatus() {
        Long encomendaId = 1L;
        Encomenda encomenda = new Encomenda();
        encomenda.setId(encomendaId);
    }
}