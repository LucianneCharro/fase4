package com.fiap.gerenciamento_encomendas.service;

import com.fiap.gerenciamento_encomendas.model.Notificacao;
import com.fiap.gerenciamento_encomendas.repository.NotificacaoRepository;
import com.fiap.gerenciamento_encomendas.service.notificacao.NotificacaoService;
import com.fiap.gerenciamento_encomendas.service.notificacao.NotificacaoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class NotificacaoServiceTest {

    @Mock
    private NotificacaoStrategy notificacaoStrategy;

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @BeforeEach
    void setUp() {
        notificacaoService = new NotificacaoService(notificacaoStrategy, notificacaoRepository);
    }

    @Test
    void enviarNotificacao_ShouldInvokeNotificacaoStrategy() {
        String destinatario = "test@example.com";
        String mensagem = "Test message";

        notificacaoService.enviarNotificacao(destinatario, mensagem);

        verify(notificacaoStrategy, times(1)).enviarNotificacao(destinatario, mensagem);
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