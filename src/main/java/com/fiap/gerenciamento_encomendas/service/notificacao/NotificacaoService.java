package com.fiap.gerenciamento_encomendas.service.notificacao;

import com.fiap.gerenciamento_encomendas.repository.NotificacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public ResponseEntity<?> confirmarRecebimento(Long id) {
        return notificacaoRepository.findById(id).map(notificacao -> {
            notificacao.setConfirmed(true);
            notificacaoRepository.save(notificacao);
            return ResponseEntity.ok().body("Notificação confirmada com sucesso.");
        }).orElse(ResponseEntity.notFound().build());
    }
}