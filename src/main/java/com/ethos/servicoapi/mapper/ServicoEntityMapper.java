package com.ethos.servicoapi.mapper;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.repository.entity.ServicoEntity;

public class ServicoEntityMapper {

    public static ServicoEntity of (ServicoRequest request){
        ServicoEntity entity = new ServicoEntity(
                null,
                request.nomeServico(),
                request.descricao(),
                request.valor(),
                request.areaAtuacaoEsg()
        );
        return entity;
    }
}
