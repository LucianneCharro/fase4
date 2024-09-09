package com.fiap.gerenciamento_encomendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ENCOMENDA")
public class Encomenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMorador;
    private String emailMorador;
    private String numeroApartamento;
    private String descricao;
    private String status;
}
