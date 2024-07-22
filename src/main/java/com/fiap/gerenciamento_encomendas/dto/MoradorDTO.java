package com.fiap.gerenciamento_encomendas.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MoradorDTO {
    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "O formato do telefone deve ser (XX) XXXXX-XXXX.")
    private String telefone;

    @NotBlank(message = "O número do apartamento é obrigatório.")
    @Size(min = 1, max = 6, message = "O número do apartamento deve ter entre 1 e 6 caracteres.")
    private String apartamento;

}