package com.ethos.servicoapi.repository;

import com.ethos.servicoapi.repository.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Integer> {
    // Dinamic Finder (Criação de um novo método com JPA para buscar uma informação específica)
    List<ServicoEntity> findByNomeContains(String nome);

    List<ServicoEntity> findByDescricaoContains(String descricao);

    List<ServicoEntity> findByValoroLessThanEqual(Double valor);

    List<ServicoEntity> findByNomeContainsAndDescricaoContains(String nome, String descricao);

    List<ServicoEntity> findByAreaAtuacaoEsg(String areaAtuacaoEsg);
}
