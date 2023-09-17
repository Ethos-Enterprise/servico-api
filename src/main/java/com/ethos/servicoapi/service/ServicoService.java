package com.ethos.servicoapi.service;

import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.mapper.ServicoEntityMapper;
import com.ethos.servicoapi.repository.ServicoRepository;
import com.ethos.servicoapi.repository.entity.ServicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoResponse postServico(ServicoRequest request) {
        final ServicoEntity novoServico = ServicoEntityMapper.of(request);
        repository.save(novoServico);
        return new ServicoResponse(novoServico);
    }

    public List<ServicoResponse> getServico() {
        final List<ServicoEntity> listaServicos = repository.findAll();
        return listaServicos.stream().map(ServicoResponse::new).toList();
    }
    public ServicoResponse getServicoById(Integer id){
        Optional<ServicoEntity> servico = repository.findById(id);
        if (servico.isEmpty()){
            return null;
        }
        return servico.map(ServicoResponse::new).get();
    }

    public ServicoResponse getServicoByNome(String nome){
        Optional<ServicoEntity> servico = repository.findByNome(nome);
        if (servico.isEmpty()){
            return null;
        }
        return servico.map(ServicoResponse::new).get();
    }

    public ServicoResponse getServicoByDescricao(String descricao){
        Optional<ServicoEntity> servico = repository.findByDescricao(descricao);
        if(servico.isEmpty()){
            return null;
        }
        return servico.map(ServicoResponse::new).get();
    }

    public ServicoResponse getServicoByValor(Double valor){
        Optional<ServicoEntity> servico = repository.findByValor(valor);
        if(servico.isEmpty()){
            return null;
        }
        return servico.map(ServicoResponse::new).get();
    }

    public ServicoResponse getServicoByNomeAndDescricao(String nome, String descricao){
        Optional<ServicoEntity> servico = repository.findByNomeAndDescricao(nome, descricao);
        if (servico.isEmpty()){
            return null;
        }
        return servico.map(ServicoResponse::new).get();
    }

    public ServicoResponse putServico(Integer id, ServicoRequest request) {
        final Optional<ServicoEntity> servicoAtualizado = repository.findById(id);

        if (servicoAtualizado.isEmpty()){
            return null;
        }
        if (request.nomeServico() != null && !request.nomeServico().isEmpty()) {
            servicoAtualizado.get().setNome(request.nomeServico());
        }
        if (request.descricao() != null && !request.descricao().isEmpty()){
            servicoAtualizado.get().setDescricao(request.descricao());
        }
        if (request.valor() != null && request.valor() > 0){
            servicoAtualizado.get().setValor(request.valor());
        }

        repository.save(servicoAtualizado.get());
        return servicoAtualizado.map(ServicoResponse::new).get();
    }

    public String deleteServico(Integer id){
        repository.deleteById(id);
        return "Serviço deletado com sucesso.";
    }

}
