package com.ethos.servicoapi.repository;

import com.ethos.servicoapi.repository.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Integer> {
    // Dinamic Finder (Criação de um novo método com JPA para buscar uma informação específica)
    Optional<ServicoEntity> findByNome(String nome);

    Optional<ServicoEntity> findByDescricao(String descricao);

    Optional<ServicoEntity> findByValor(Double valor);

    Optional<ServicoEntity> findByNomeAndDescricao(String nome, String descricao);

    Optional<ServicoEntity> findByAreaAtuacaoEsg(String areaAtuacaoEsg);
}
