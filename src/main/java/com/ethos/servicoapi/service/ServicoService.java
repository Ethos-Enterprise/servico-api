package com.ethos.servicoapi.service;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.mapper.ServicoEntityMapper;
import com.ethos.servicoapi.mapper.ServicoMapper;
import com.ethos.servicoapi.repository.ServicoRepository;
import com.ethos.servicoapi.repository.entity.ServicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoResponse postServico(ServicoRequest request) {
        final ServicoEntity novoServico = ServicoEntityMapper.of(request);
        repository.save(novoServico);
        return new ServicoResponse(novoServico);
    }
}
