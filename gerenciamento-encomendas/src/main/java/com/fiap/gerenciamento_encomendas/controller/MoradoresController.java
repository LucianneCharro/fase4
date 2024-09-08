package com.fiap.gerenciamento_encomendas.controller;
import com.fiap.gerenciamento_encomendas.dto.MoradorDTO;
import com.fiap.gerenciamento_encomendas.service.moradores.MoradoresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastrar-moradores")
@Tag(name = "Moradores", description = "API para gerenciamento de moradores do condominio")
public class MoradoresController {

    @Autowired
    private MoradoresService moradoresService;

    @GetMapping
    @Operation(summary = "Listar todos os moradores")
    public ResponseEntity<List<MoradorDTO>> listarTodos() {
        List<MoradorDTO> moradores = moradoresService.listarTodos();
        return ResponseEntity.ok(moradores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id", description = "Buscar morador por id", tags = "moradores")
    public ResponseEntity<MoradorDTO> buscarPorId(@PathVariable Long id) {
        MoradorDTO moradorDTO = moradoresService.buscarPorId(id);
        return ResponseEntity.ok(moradorDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar morador", tags = "moradores")
    public ResponseEntity<MoradorDTO> cadastrarMorador(@Valid @RequestBody MoradorDTO moradorDTO) {
        MoradorDTO moradorCriado = moradoresService.cadastrarMorador(moradorDTO);
        return new ResponseEntity<>(moradorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar morador por id", tags = "moradores")
    public ResponseEntity<?> atualizarMorador(@PathVariable Long id, @Valid @RequestBody MoradorDTO moradorDTO) {
        moradoresService.atualizarMorador(id, moradorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirMorador(@PathVariable Long id) {
        moradoresService.excluirMorador(id);
        return ResponseEntity.ok().build();
    }
}