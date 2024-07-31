package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Notificacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class NotificacaoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Test
    public void findByConfirmed_WhenConfirmedIsTrue_ShouldReturnConfirmedNotifications() {
        Notificacao confirmedNotificacao = new Notificacao();
        confirmedNotificacao.setConfirmed(true);
        entityManager.persistAndFlush(confirmedNotificacao);

        Notificacao notConfirmedNotificacao = new Notificacao();
        notConfirmedNotificacao.setConfirmed(false);
        entityManager.persistAndFlush(notConfirmedNotificacao);

        List<Notificacao> result = notificacaoRepository.findByConfirmed(true);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).isConfirmed()).isTrue();
    }

    @Test
    public void findByConfirmed_WhenConfirmedIsFalse_ShouldReturnNotConfirmedNotifications() {
        Notificacao confirmedNotificacao = new Notificacao();
        confirmedNotificacao.setConfirmed(true);
        entityManager.persistAndFlush(confirmedNotificacao);

        Notificacao notConfirmedNotificacao = new Notificacao();
        notConfirmedNotificacao.setConfirmed(false);
        entityManager.persistAndFlush(notConfirmedNotificacao);

        List<Notificacao> result = notificacaoRepository.findByConfirmed(false);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).isConfirmed()).isFalse();
    }
}