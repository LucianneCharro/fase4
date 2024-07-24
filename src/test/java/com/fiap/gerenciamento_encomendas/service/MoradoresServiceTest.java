package com.fiap.gerenciamento_encomendas.service;

import com.fiap.gerenciamento_encomendas.dto.MoradorDTO;
import com.fiap.gerenciamento_encomendas.model.Morador;
import com.fiap.gerenciamento_encomendas.repository.MoradoresRepository;
import com.fiap.gerenciamento_encomendas.service.moradores.MoradoresService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoradoresServiceTest {

    @Mock
    private MoradoresRepository moradoresRepository;

    @InjectMocks
    private MoradoresService moradoresService;

    private MoradorDTO moradorDTO;
    private Morador morador;

    @BeforeEach
    void setUp() {
        moradorDTO = new MoradorDTO();
        moradorDTO.setTelefone("(11) 1234-5678");
        moradorDTO.setApartamento("101B");

        morador = new Morador();
        morador.setTelefone(moradorDTO.getTelefone());
        morador.setApartamento(moradorDTO.getApartamento());
    }

    @Test
    void cadastrarMorador_ShouldReturnMoradorDTO() {
        when(moradoresRepository.save(any(Morador.class))).thenReturn(morador);

        MoradorDTO result = moradoresService.cadastrarMorador(moradorDTO);

        assertThat(result.getTelefone()).isEqualTo(moradorDTO.getTelefone());
        assertThat(result.getApartamento()).isEqualTo(moradorDTO.getApartamento());
    }

    @Test
    void atualizarMorador_ShouldUpdateSuccessfully() {
        when(moradoresRepository.findById(anyLong())).thenReturn(Optional.of(morador));
        when(moradoresRepository.save(any(Morador.class))).thenReturn(morador);

        assertDoesNotThrow(() -> moradoresService.atualizarMorador(1L, moradorDTO));
    }

    @Test
    void listarTodos_ShouldReturnListOfMoradorDTO() {
        when(moradoresRepository.findAll()).thenReturn(Arrays.asList(morador));

        List<MoradorDTO> result = moradoresService.listarTodos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTelefone()).isEqualTo(morador.getTelefone());
    }

    @Test
    void buscarPorId_ShouldReturnMoradorDTO() {
        when(moradoresRepository.findById(anyLong())).thenReturn(Optional.of(morador));

        MoradorDTO result = moradoresService.buscarPorId(1L);

        assertThat(result.getTelefone()).isEqualTo(morador.getTelefone());
    }

    @Test
    void excluirMorador_ShouldCallDelete() {
        when(moradoresRepository.findById(anyLong())).thenReturn(Optional.of(morador));
        doNothing().when(moradoresRepository).delete(any(Morador.class));

        assertDoesNotThrow(() -> moradoresService.excluirMorador(1L));
    }

    @Test
    void cadastrarMorador_WithInvalidTelefone_ShouldThrowException() {
        moradorDTO.setTelefone("invalid_phone");

        assertThrows(NullPointerException.class, () -> moradoresService.cadastrarMorador(moradorDTO),
                "O formato do telefone é inválido.");
    }
}