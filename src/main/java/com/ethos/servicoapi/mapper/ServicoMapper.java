package com.ethos.servicoapi.mapper;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.repository.entity.ServicoEntity;
import com.ethos.servicoapi.repository.entity.esgenum.AreaAtuacaoEsgEnum;

import java.util.List;

public class ServicoMapper {

    public static ServicoEntity of(ServicoRequest servicoRequest){
        ServicoEntity servicoEntity = new ServicoEntity();
        List<AreaAtuacaoEsgEnum> areaAtuacaoEsg = servicoRequest.areaAtuacaoEsg().stream().map(areaAtuacaoEsgEnum -> areaAtuacaoEsgEnum).toList();

        servicoEntity.setNome(servicoRequest.nomeServico());
        servicoEntity.setDescricao(servicoRequest.descricao());
        servicoEntity.setValor(servicoRequest.valor());
        servicoEntity.setAreaAtuacaoEsg(areaAtuacaoEsg);
        servicoEntity.setFkPrestadoraServico(servicoRequest.fkPrestadoraServico());

        return servicoEntity;
    }
}
