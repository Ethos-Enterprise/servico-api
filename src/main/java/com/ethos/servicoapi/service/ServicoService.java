package com.ethos.servicoapi.service;

import com.ethos.servicoapi.api.PrestadoraApiClient;
import com.ethos.servicoapi.api.prestadoradto.Prestadora;
import com.ethos.servicoapi.controller.request.ServicoRequest;
import com.ethos.servicoapi.controller.response.ServicoResponse;
import com.ethos.servicoapi.exception.PrestadoraNaoAprovadaException;
import com.ethos.servicoapi.exception.PrestadoraNotFoundException;
import com.ethos.servicoapi.exception.ServicoException;
import com.ethos.servicoapi.exception.ServicoNotFoundException;
import com.ethos.servicoapi.mapper.ServicoMapper;
import com.ethos.servicoapi.repository.ServicoRepository;
import com.ethos.servicoapi.repository.entity.ServicoEntity;
import com.ethos.servicoapi.repository.entity.esgenum.AreaAtuacaoEsgEnum;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;
    private final PrestadoraApiClient client;

    public ServicoResponse postServico(ServicoRequest request) {
        final ServicoEntity novoServico = ServicoMapper.of(request);

        try {
            Prestadora prestadora = client.getPrestadoraById(request.fkPrestadoraServico());

            String statusAprovacao = prestadora.statusAprovacao();
            if (!statusAprovacao.equals("APROVADO")){
                throw new PrestadoraNaoAprovadaException("Prestadora não aprovada");
            }
        } catch (FeignException e){
            throw new PrestadoraNotFoundException(e.getMessage());
        }

        repository.save(novoServico);
        return new ServicoResponse(novoServico);
    }

    public List<ServicoResponse> getServico() {
        final List<ServicoEntity> listaServicos = repository.findAll();
        return listaServicos.stream().map(ServicoResponse::new).toList();
    }
    public ServicoResponse getServicoById(UUID id){
        Optional<ServicoEntity> servico = repository.findById(id);
        if (servico.isEmpty()){
            throw new ServicoNotFoundException(String.format("Serviço não encontrado."));
        }
        return servico.map(ServicoResponse::new).get();
    }

    public List<ServicoResponse> getServicoByNome(String nome){
        List<ServicoEntity> servico = repository.findByNomeContainsIgnoreCase(nome);
        if (servico.isEmpty()){
            throw new ServicoException(String.format("O serviço com o nome %s não existe.", nome));
        }
        return servico.stream().map(ServicoResponse::new).toList();
    }

    public List<ServicoResponse> getServicoByDescricao(String descricao){
        List<ServicoEntity> servico = repository.findByDescricaoContainsIgnoreCase(descricao);
        if(servico.isEmpty()){
            throw new ServicoException(String.format("O serviço com a descrição %s não existe.", descricao));
        }
        return servico.stream().map(ServicoResponse::new).toList();
    }

    public List<ServicoResponse> getServicoByValor(Double valor){
        List<ServicoEntity> servico = repository.findByValorLessThanEqual(valor);
        if(servico.isEmpty()){
            throw new ServicoException(String.format("O serviço com o valor R$%.2f ou abaixo dele não existe.", valor));
        }
        return servico.stream().map(ServicoResponse::new).toList();
    }

    public List<ServicoResponse> getServicoByNomeAndDescricao(String nome, String descricao){
        List<ServicoEntity> servico = repository.findByNomeContainsIgnoreCaseAndDescricaoContainsIgnoreCase(nome, descricao);
        if (servico.isEmpty()){
            throw new ServicoException(String.format("O serviço com o nome %s e descrição %s não existe.", nome, descricao));
        }
        return servico.stream().map(ServicoResponse::new).toList();
    }

    public List<ServicoResponse> getServicoByAreaAtuacaoEsg(List<String> areaAtuacaoEsg){
        List<ServicoEntity> servico = repository.findByAreaAtuacaoEsg(areaAtuacaoEsg);
        if(servico.isEmpty()){
            throw new ServicoException(String.format("O serviço com a área de atuação %s não existe", areaAtuacaoEsg));
        }
        return servico.stream().map(ServicoResponse::new).toList();
    }

    public ServicoResponse putServico(UUID id, ServicoRequest request) {
        final Optional<ServicoEntity> servicoAtualizado = repository.findById(id);

        if (servicoAtualizado.isEmpty()){
            throw new ServicoNotFoundException(String.format("Serviço não encontrado."));
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
        if (request.areaAtuacaoEsg() != null && !request.areaAtuacaoEsg().isEmpty()){
            List<String> areaAtuacaoEsg = request.areaAtuacaoEsg().stream().map(areaAtuacaoEsgEnum -> areaAtuacaoEsgEnum.toString()).toList();
            servicoAtualizado.get().setAreaAtuacaoEsg(areaAtuacaoEsg);
        }

        repository.save(servicoAtualizado.get());
        return servicoAtualizado.map(ServicoResponse::new).get();
    }

    public String deleteServico(UUID id){
        repository.deleteById(id);
        return "Serviço deletado com sucesso.";
    }
}
