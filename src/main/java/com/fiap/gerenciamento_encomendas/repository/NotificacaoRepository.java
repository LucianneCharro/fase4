package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByConfirmed(boolean confirmed);

}
