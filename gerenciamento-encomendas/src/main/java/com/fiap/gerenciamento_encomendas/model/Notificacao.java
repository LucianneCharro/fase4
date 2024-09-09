package com.fiap.gerenciamento_encomendas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "NOTIFICACAO")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long residentId;
    private String message;
    private boolean delivered;
    private boolean confirmed;
}