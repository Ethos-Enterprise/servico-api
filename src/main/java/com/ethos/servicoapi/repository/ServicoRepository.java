package com.ethos.servicoapi.repository;

import com.ethos.servicoapi.repository.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Integer> {
    List<ServicoEntity> findByNomeContainsIgnoreCase(String nome);

    List<ServicoEntity> findByDescricaoContainsIgnoreCase(String descricao);

    List<ServicoEntity> findByValorLessThanEqual(Double valor);

    List<ServicoEntity> findByNomeContainsIgnoreCaseAndDescricaoContainsIgnoreCase(String nome, String descricao);

    List<ServicoEntity> findByAreaAtuacaoEsgContainsIgnoreCase(String areaAtuacaoEsg);
}
