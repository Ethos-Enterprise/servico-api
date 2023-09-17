package com.ethos.servicoapi.controller.response;

import com.ethos.servicoapi.repository.entity.ServicoEntity;

public record ServicoResponse(Integer id, String nomeServico, String descricao, Double valor) {

    public ServicoResponse(ServicoEntity servicoEntity){
        this(
                servicoEntity.getId(),
                servicoEntity.getNomeServico(),
                servicoEntity.getDescricao(),
                servicoEntity.getValor()
        );
    }
}
