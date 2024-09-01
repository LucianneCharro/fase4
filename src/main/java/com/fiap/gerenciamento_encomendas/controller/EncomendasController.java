package com.fiap.gerenciamento_encomendas.controller;
import com.fiap.gerenciamento_encomendas.dto.EncomendaDTO;
import com.fiap.gerenciamento_encomendas.service.encomendas.EncomendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encomendas-portaria")
public class EncomendasController {
    @Autowired
    private EncomendasService encomendasService;

    @PostMapping("/receber")
    public ResponseEntity<?> receberEncomendasPortaria(@RequestBody EncomendaDTO encomendaDTO) {
        encomendasService.receberEncomendaPortaria(encomendaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/processar")
    public ResponseEntity<?> processarEncomendasPortaria() {
        encomendasService.processarEncomendasPortaria();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/retirar-morador/{id}")
    public ResponseEntity<?> registrarRetirada(@PathVariable Long id) {
        encomendasService.registrarRetirada(id);
        return ResponseEntity.ok().build();
    }
}