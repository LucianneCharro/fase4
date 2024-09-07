package com.fiap.gerenciamento_encomendas.service;

import com.fiap.gerenciamento_encomendas.model.Notificacao;
import com.fiap.gerenciamento_encomendas.repository.NotificacaoRepository;
import com.fiap.gerenciamento_encomendas.service.notificacao.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class NotificacaoServiceTest {

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @BeforeEach
    void setUp() {
        notificacaoService = new NotificacaoService(notificacaoRepository);
    }

    @Test
    void confirmarRecebimento_WhenFound_ShouldReturnOkResponse() {
        Long id = 1L;
        Notificacao notificacao = new Notificacao();
        notificacao.setId(id);
        notificacao.setConfirmed(false);

        when(notificacaoRepository.findById(id)).thenReturn(Optional.of(notificacao));

        ResponseEntity<?> response = notificacaoService.confirmarRecebimento(id);

        verify(notificacaoRepository, times(1)).save(notificacao);
        assertThat(notificacao.isConfirmed()).isTrue();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void confirmarRecebimento_WhenNotFound_ShouldReturnNotFoundResponse() {
        Long id = 1L;

        when(notificacaoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> response = notificacaoService.confirmarRecebimento(id);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}