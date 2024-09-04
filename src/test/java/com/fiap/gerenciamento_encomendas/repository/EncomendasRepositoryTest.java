package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Encomenda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class EncomendasRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EncomendasRepository encomendasRepository;

    @Test
    public void findByNomeMoradorAndDescricao_WhenMatchExists_ShouldReturnEncomenda() {
        // Given
        Encomenda encomenda = new Encomenda();
        encomenda.setNomeMorador("John Doe");
        encomenda.setDescricao("Package 1");
        entityManager.persist(encomenda);
        entityManager.flush();

        // When
        Encomenda found = encomendasRepository.findByNomeMoradorAndDescricao("John Doe", "Package 1");

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getNomeMorador()).isEqualTo(encomenda.getNomeMorador());
        assertThat(found.getDescricao()).isEqualTo(encomenda.getDescricao());
    }
}