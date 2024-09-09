package com.fiap.gerenciamento_encomendas.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MoradorDTO {

    @NotBlank(message = "O nome do morador é obrigatório.")
    private String nome;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "O formato do telefone deve ser (XX) XXXXX-XXXX.")
    private String telefone;

    @NotBlank(message = "O número do apartamento é obrigatório.")
    @Size(min = 1, max = 6, message = "O número do apartamento deve ter entre 1 e 6 caracteres.")
    private String apartamento;

    @NotBlank(message = "O e-mail do morador é obrigatório.")
    private String email;

}