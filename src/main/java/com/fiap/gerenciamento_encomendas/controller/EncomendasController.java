package com.fiap.gerenciamento_encomendas.controller;
import com.fiap.gerenciamento_encomendas.dto.EncomendaDTO;
import com.fiap.gerenciamento_encomendas.service.encomendas.EncomendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encomendas")
public class EncomendasController {
    @Autowired
    private EncomendasService encomendasService;

    @PostMapping("/receber")
    public ResponseEntity<?> receberEncomenda(@RequestBody EncomendaDTO encomendaDTO) {
        encomendasService.adicionarEncomenda(encomendaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/processar")
    public ResponseEntity<?> processarEncomendas() {
        encomendasService.processarEncomendas();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/retirar/{id}")
    public ResponseEntity<?> registrarRetirada(@PathVariable Long id) {
        encomendasService.registrarRetirada(id);
        return ResponseEntity.ok().build();
    }
}