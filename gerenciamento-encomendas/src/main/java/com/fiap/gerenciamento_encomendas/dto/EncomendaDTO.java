package com.fiap.gerenciamento_encomendas.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EncomendaDTO {
    @NotBlank(message = "O nome do morador é obrigatório.")
    private String nomeMorador;

    @NotBlank(message = "O email do morador é obrigatório.")
    private String emailMorador;

    @NotBlank(message = "O número do apartamento é obrigatório.")
    @Size(min = 1, max = 6, message = "O número do apartamento deve ter entre 1 e 6 caracteres.")
    private String numeroApartamento;

    @NotBlank(message = "A descrição da encomenda é obrigatória.")
    @Size(max = 255, message = "A descrição da encomenda deve ter no máximo 255 caracteres.")
    private String descricao;

    private String status;
}