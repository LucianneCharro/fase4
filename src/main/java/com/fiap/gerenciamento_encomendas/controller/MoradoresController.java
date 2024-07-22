package com.fiap.gerenciamento_encomendas.controller;
import com.fiap.gerenciamento_encomendas.dto.MoradorDTO;
import com.fiap.gerenciamento_encomendas.service.moradores.MoradoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/moradores")
public class MoradoresController {

    @Autowired
    private MoradoresService moradoresService;

    @GetMapping
    public ResponseEntity<List<MoradorDTO>> listarTodos() {
        List<MoradorDTO> moradores = moradoresService.listarTodos();
        return ResponseEntity.ok(moradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradorDTO> buscarPorId(@PathVariable Long id) {
        MoradorDTO moradorDTO = moradoresService.buscarPorId(id);
        return ResponseEntity.ok(moradorDTO);
    }

    @PostMapping
    public ResponseEntity<MoradorDTO> cadastrarMorador(@Valid @RequestBody MoradorDTO moradorDTO) {
        MoradorDTO moradorCriado = moradoresService.cadastrarMorador(moradorDTO);
        return new ResponseEntity<>(moradorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
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