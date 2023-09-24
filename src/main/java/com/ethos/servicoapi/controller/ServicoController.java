package com.ethos.servicoapi.controller;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1.0/servicos")
@RequiredArgsConstructor // Injeção de dependência automática de todos os atributos
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
    public ServicoResponse getServicoById(@PathVariable Integer id){
        return servicoService.getServicoById(id);
    }

    @GetMapping("/busca-por-nome")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse getServicoByNome(@RequestParam String nome){
        return servicoService.getServicoByNome(nome);
    }

    @GetMapping("/busca-por-descricao")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse getServicoByDescricao(@RequestParam String descricao){
        return servicoService.getServicoByDescricao(descricao);
    }

    @GetMapping("/busca-por-valor")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse getServicoByValor(@RequestParam Double valor){
        return servicoService.getServicoByValor(valor);
    }

    @GetMapping("/busca-area-atuacao")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse getServicoByAreaAtuacaoEsg(@RequestParam String areaAtuacaoEsg){
        return servicoService.getServicoByAreaAtuacaoEsg(areaAtuacaoEsg);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponse putServico(@PathVariable Integer id, @RequestBody @Valid ServicoRequest request){
        return servicoService.putServico(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteServico(@PathVariable Integer id){
        return servicoService.deleteServico(id);
    }
}
