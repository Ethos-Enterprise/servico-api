package com.ethos.servicoapi.controller.response;

import com.ethos.servicoapi.repository.entity.ServicoEntity;

import java.util.UUID;

public record ServicoResponse(Integer id, String nomeServico, String descricao, Double valor, String areaAtuacaoEsg, UUID fkPrestadoraServico) {

    public ServicoResponse(ServicoEntity servicoEntity){
        this(
                servicoEntity.getId(),
                servicoEntity.getNome(),
                servicoEntity.getDescricao(),
                servicoEntity.getValor(),
                servicoEntity.getAreaAtuacaoEsg(),
                servicoEntity.getFkPrestadoraServico()
        );
    }
}
