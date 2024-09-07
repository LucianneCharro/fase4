package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EncomendasRepository extends JpaRepository<Encomenda, Long> {
    @Query("SELECT e FROM Encomenda e WHERE e.nomeMorador = :nomeMorador AND e.descricao = :descricao")
    Encomenda findByNomeMoradorAndDescricao(@Param("nomeMorador") String nomeMorador, @Param("descricao") String descricao);
}
