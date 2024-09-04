package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}