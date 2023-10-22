package com.ethos.servicoapi.mapper;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.repository.entity.ServicoEntity;

public class ServicoMapper {

    public static ServicoEntity of(ServicoRequest servicoRequest){
        ServicoEntity servicoEntity = new ServicoEntity();

        servicoEntity.setNome(servicoRequest.nomeServico());
        servicoEntity.setDescricao(servicoRequest.descricao());
        servicoEntity.setValor(servicoRequest.valor());
        servicoEntity.setAreaAtuacaoEsg(servicoRequest.areaAtuacaoEsg());
        servicoEntity.setFkPrestadoraServico(servicoRequest.fkPrestadoraServico());

        return servicoEntity;
    }
}
