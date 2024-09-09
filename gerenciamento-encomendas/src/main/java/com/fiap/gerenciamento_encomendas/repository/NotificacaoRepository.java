package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByConfirmed(boolean confirmed);

}
