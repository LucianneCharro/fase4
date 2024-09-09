package com.fiap.gerenciamento_encomendas.controller;

import com.fiap.gerenciamento_encomendas.service.notificacao.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/notificacoes")
@Tag(name = "Notificacoes", description = "API para gerenciamento de notificacoes")
public class NotificacaoController {
    @Autowired
    NotificacaoService notificacaoService;
    @PostMapping("/confirmar/{id}")
    @Operation(summary = "Confirmar recebimento", description = "Confirmar recebimento de notificacao", tags = "notificacoes")
    public ResponseEntity<?> confirmarRecebimento(@PathVariable Long id) {
        return notificacaoService.confirmarRecebimento(id);
    }
}
