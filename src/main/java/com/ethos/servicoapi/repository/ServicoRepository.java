package com.ethos.servicoapi.repository;

import com.ethos.servicoapi.repository.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoEntity, UUID> {
    List<ServicoEntity> findByNomeContainsIgnoreCase(String nome);

    List<ServicoEntity> findByDescricaoContainsIgnoreCase(String descricao);

    List<ServicoEntity> findByValorLessThanEqual(Double valor);

    List<ServicoEntity> findByNomeContainsIgnoreCaseAndDescricaoContainsIgnoreCase(String nome, String descricao);

    List<ServicoEntity> findByAreaAtuacaoEsgContainsIgnoreCase(String areaAtuacaoEsg);
}
