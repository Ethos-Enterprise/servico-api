package com.ethos.servicoapi.controller;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1.0/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponse postServico(@RequestBody @Valid ServicoRequest request){
        return servicoService.postServico(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getServico(){
        return servicoService.getServico();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse getServicoById(@PathVariable UUID id){
        return servicoService.getServicoById(id);
    }

    @GetMapping("/busca-por-nome")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getServicoByNome(@RequestParam String nome){
        return servicoService.getServicoByNome(nome);
    }

    @GetMapping("/busca-por-descricao")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getServicoByDescricao(@RequestParam String descricao){
        return servicoService.getServicoByDescricao(descricao);
    }

    @GetMapping("/busca-por-valor")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getServicoByValor(@RequestParam Double valor){
        return servicoService.getServicoByValor(valor);
    }

    @GetMapping("/busca-por-nome-descricao")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getServicoByNomeAndDescricao(@RequestParam String nome, @RequestParam String descricao){
        return servicoService.getServicoByNomeAndDescricao(nome, descricao);
    }

    //@GetMapping("/busca-area-atuacao")
    //@ResponseStatus(HttpStatus.OK)
    //public List<ServicoResponse> getServicoByAreaAtuacaoEsg(@RequestParam List<String> areaAtuacaoEsg){
    //    return servicoService.getServicoByAreaAtuacaoEsg(areaAtuacaoEsg);
    //}

    @GetMapping("/busca-area-atuacao")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getServicoByAreaAtuacaoEsg(@RequestParam String areaAtuacaoEsg){
        return servicoService.getServicoByAreaAtuacaoEsg(areaAtuacaoEsg);
    }

    @GetMapping("/fila-exibicao-curtidas")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponse> getFilaServico(@RequestBody ServicoResponse servicoResponse){
        return servicoService.filaServico(servicoResponse);
    }

    @GetMapping("/lista-servicos/{fkPrestadoraServico}")
    @ResponseStatus
    public List<ServicoResponse> getServicoByFkPrestadoraServico(@PathVariable UUID fkPrestadoraServico){
        return servicoService.getServicoByFkPrestadoraServico(fkPrestadoraServico);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse putServico(@PathVariable UUID id, @RequestBody @Valid ServicoRequest request){
        return servicoService.putServico(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@PathVariable UUID id){
        servicoService.deleteServico(id);
    }
}
