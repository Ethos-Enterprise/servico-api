package com.ethos.servicoapi.controller;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1.0/servicos")
@RequiredArgsConstructor // Injeção de dependência automática de todos os atributos
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponse postEmpresa(@RequestBody @Valid ServicoRequest request) {
        return servicoService.postServico(request);
    }
}
