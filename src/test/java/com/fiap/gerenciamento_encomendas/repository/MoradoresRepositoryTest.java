package com.fiap.gerenciamento_encomendas.repository;

import com.fiap.gerenciamento_encomendas.model.Morador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MoradoresRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MoradoresRepository moradoresRepository;

    @Test
    public void whenSave_thenReturnMorador() {
        Morador morador = new Morador();
        morador.setTelefone("996117524");
        morador.setApartamento("101B");

        Morador savedMorador = moradoresRepository.save(morador);

        Morador found = entityManager.find(Morador.class, savedMorador.getId());
        assertThat(found.getTelefone()).isEqualTo(morador.getTelefone());
        assertThat(found.getApartamento()).isEqualTo(morador.getApartamento());
    }

    @Test
    public void whenFindById_thenReturnMorador() {
        Morador morador = new Morador();
        morador.setTelefone("996117524");
        morador.setApartamento("102C");
        Morador persisted = entityManager.persistFlushFind(morador);

        Optional<Morador> found = moradoresRepository.findById(persisted.getId());

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getTelefone()).isEqualTo(morador.getTelefone());
        assertThat(found.get().getApartamento()).isEqualTo(morador.getApartamento());
    }

    @Test
    public void whenDelete_thenRemoveMorador() {
        Morador morador = new Morador();
        morador.setTelefone("996117524");
        morador.setApartamento("103D");
        Morador persisted = entityManager.persistFlushFind(morador);
        Long moradorId = persisted.getId();

        moradoresRepository.deleteById(moradorId);
        entityManager.flush();

        Morador found = entityManager.find(Morador.class, moradorId);
        assertThat(found).isNull();
    }
}