package com.fiap.gerenciamento_encomendas.service.moradores;

import com.fiap.gerenciamento_encomendas.dto.MoradorDTO;
import com.fiap.gerenciamento_encomendas.model.Morador;
import com.fiap.gerenciamento_encomendas.repository.MoradoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoradoresService {

    @Autowired
    private MoradoresRepository moradoresRepository;

    public MoradorDTO cadastrarMorador(MoradorDTO moradorDTO) {
        Morador morador = convertToEntity(moradorDTO);
        Morador moradorSalvo = moradoresRepository.save(morador);
        return convertToDto(moradorSalvo);
    }

    public void atualizarMorador(Long id, MoradorDTO moradorDTO) {
        Morador morador = moradoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        updateEntity(morador, moradorDTO);
        moradoresRepository.save(morador);
    }

    public List<MoradorDTO> listarTodos() {
        return moradoresRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MoradorDTO buscarPorId(Long id) {
        Morador morador = moradoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        return convertToDto(morador);
    }

    public void excluirMorador(Long id) {
        Morador morador = moradoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        moradoresRepository.delete(morador);
    }

    private Morador convertToEntity(MoradorDTO moradorDTO) {
        if (moradorDTO.getNome() == null || moradorDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do morador é obrigatório.");
        }
        if (moradorDTO.getTelefone() == null || moradorDTO.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone é obrigatório.");
        }
        if (moradorDTO.getApartamento() == null || moradorDTO.getApartamento().trim().isEmpty()) {
            throw new IllegalArgumentException("O número do apartamento é obrigatório.");
        }
        if (moradorDTO.getEmail() == null || moradorDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do morador é obrigatório.");
        }
        Morador morador = new Morador();
        morador.setNome(moradorDTO.getNome());
        morador.setTelefone(moradorDTO.getTelefone());
        morador.setApartamento(moradorDTO.getApartamento());
        morador.setEmail(moradorDTO.getEmail());
        return morador;
    }

    private void updateEntity(Morador morador, MoradorDTO moradorDTO) {
        if (moradorDTO.getTelefone() == null || !moradorDTO.getTelefone().matches("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$")) {
            throw new IllegalArgumentException("O formato do telefone é inválido.");
        }
        if (moradorDTO.getApartamento() == null || moradorDTO.getApartamento().length() < 1 || moradorDTO.getApartamento().length() > 6) {
            throw new IllegalArgumentException("O número do apartamento deve ter entre 1 e 6 caracteres.");
        }
        morador.setTelefone(moradorDTO.getTelefone());
        morador.setApartamento(moradorDTO.getApartamento());
        morador.setNome(moradorDTO.getNome());
        morador.setEmail(moradorDTO.getEmail());
    }

    private MoradorDTO convertToDto(Morador morador) {
        MoradorDTO dto = new MoradorDTO();
        dto.setTelefone(morador.getTelefone());
        dto.setApartamento(morador.getApartamento());
        dto.setNome(morador.getNome());
        dto.setEmail(morador.getEmail());
        return dto;
    }
}