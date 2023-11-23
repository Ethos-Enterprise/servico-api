package com.ethos.servicoapi.repository;

import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.repository.entity.ServicoEntity;
import com.ethos.servicoapi.repository.entity.esgenum.AreaAtuacaoEsgEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoEntity, UUID> {
    List<ServicoEntity> findByNomeContainsIgnoreCase(String nome);

    List<ServicoEntity> findByDescricaoContainsIgnoreCase(String descricao);

    List<ServicoEntity> findByValorLessThanEqual(Double valor);

    List<ServicoEntity> findByNomeContainsIgnoreCaseAndDescricaoContainsIgnoreCase(String nome, String descricao);

    List<ServicoEntity> findByFkPrestadoraServico(UUID fkPrestadoraServico);

    @Query("SELECT s FROM ServicoEntity s WHERE :areaAtuacaoEsg MEMBER OF s.areaAtuacaoEsg")
    List<ServicoEntity> findByAreaAtuacaoEsg(@Param("areaAtuacaoEsg") List<String> areaAtuacaoEsg);
}
