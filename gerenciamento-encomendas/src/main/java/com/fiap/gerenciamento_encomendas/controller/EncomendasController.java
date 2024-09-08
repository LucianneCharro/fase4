package com.fiap.gerenciamento_encomendas.controller;
import com.fiap.gerenciamento_encomendas.dto.EncomendaDTO;
import com.fiap.gerenciamento_encomendas.service.encomendas.EncomendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encomendas-portaria")
@Tag(name = "Encomendas", description = "API para gerenciamento de encomendas")
public class EncomendasController {
    @Autowired
    private EncomendasService encomendasService;

    @PostMapping("/receber")
    @Operation(summary = "Receber", description = "Receber encomendas na portaria", tags = "encomendas")
    public ResponseEntity<?> receberEncomendasPortaria(@RequestBody EncomendaDTO encomendaDTO) {
        encomendasService.receberEncomendaPortaria(encomendaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/processar")
    @Operation(summary = "Processar", description = "Processar encomendas na portaria/Enviar email", tags = "encomendas")
    public ResponseEntity<?> processarEncomendasPortaria() {
        encomendasService.processarEncomendasPortaria();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/retirar-morador/{id}")
    @Operation(summary = "Registrar retirada", description = "Registrar retirada de encomenda pelo morador", tags = "encomendas")
    public ResponseEntity<?> registrarRetirada(@PathVariable Long id) {
        encomendasService.registrarRetirada(id);
        return ResponseEntity.ok().build();
    }
}