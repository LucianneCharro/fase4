package com.fiap.gerenciamento_encomendas.controller;

import com.fiap.gerenciamento_encomendas.service.notificacao.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<?> confirmarRecebimento(@PathVariable Long id) {
        return notificacaoService.confirmarRecebimento(id);
    }
}
