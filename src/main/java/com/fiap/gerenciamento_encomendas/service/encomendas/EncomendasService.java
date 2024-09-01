package com.fiap.gerenciamento_encomendas.service.encomendas;

import com.fiap.gerenciamento_encomendas.dto.EncomendaDTO;
import com.fiap.gerenciamento_encomendas.model.Encomenda;
import com.fiap.gerenciamento_encomendas.model.Notificacao;
import com.fiap.gerenciamento_encomendas.repository.EncomendasRepository;
import com.fiap.gerenciamento_encomendas.repository.NotificacaoRepository;
import com.fiap.gerenciamento_encomendas.service.notificacao.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EncomendasService {
    @Autowired
    private EncomendasRepository encomendasRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    private Queue<EncomendaDTO> filaEncomendas = new LinkedList<>();

    @Transactional
    public void receberEncomendaPortaria(EncomendaDTO encomendaDTO) {
        Encomenda encomenda = new Encomenda();
        encomenda.setNomeMorador(encomendaDTO.getNomeMorador());
        encomenda.setEmailMorador(encomendaDTO.getEmailMorador());
        encomenda.setNumeroApartamento(encomendaDTO.getNumeroApartamento());
        encomenda.setDescricao(encomendaDTO.getDescricao());
        encomenda.setStatus("Recebida"); // Define o status inicial
        encomendasRepository.save(encomenda);
        filaEncomendas.add(encomendaDTO);
    }

    @Transactional
    public void processarEncomendasPortaria() {
        while (!filaEncomendas.isEmpty()) {
            EncomendaDTO encomendaDTO = filaEncomendas.poll();
            // Enviar notificação ao morador
            try {

                // Atualizar o status da encomenda no banco de dados
                Encomenda encomenda = encomendasRepository.findByNomeMoradorAndDescricao(encomendaDTO.getNomeMorador(), encomendaDTO.getDescricao());
                String mensagem = "Sua encomenda está pronta para retirada: " + encomendaDTO.getDescricao();
                emailService.enviarEmailTexto(encomenda.getEmailMorador(), "Nova Encomenda", "Sua encomenda esta pronta para retirada");
                if (encomenda != null) {
                    encomenda.setStatus("Notificada");
                    encomendasRepository.save(encomenda);
                    Notificacao notificacao = new Notificacao();
                    notificacao.setResidentId(encomenda.getId());
                    notificacao.setMessage(mensagem);
                    notificacao.setDelivered(true);
                    notificacaoRepository.save(notificacao);
                }
            } catch (Exception e) {
                System.out.println("Erro ao enviar notificação: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
    }

    @Transactional
    public void registrarRetirada(Long encomendaId) {
        Optional<Encomenda> encomendaOpt = encomendasRepository.findById(encomendaId);
        if (encomendaOpt.isPresent()) {
            Encomenda encomenda = encomendaOpt.get();
            encomenda.setStatus("Retirada");
            encomendasRepository.save(encomenda);
        }
    }
}