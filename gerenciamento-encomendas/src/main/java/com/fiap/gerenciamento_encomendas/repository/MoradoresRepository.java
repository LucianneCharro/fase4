package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradoresRepository extends JpaRepository<Morador, Long> {

}
