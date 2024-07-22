package com.fiap.gerenciamento_encomendas.service.notificacao;

public interface NotificacaoStrategy {
    void enviarNotificacao(String destinatario, String mensagem);
}