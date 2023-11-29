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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Import(ServicoService.ServicoConfig.class)
public class ServicoService {

    private final ServicoRepository repository;
    private final PrestadoraApiClient client;
    private final ServicoFilaObj filaObj;

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

    //public List<ServicoResponse> getServicoByAreaAtuacaoEsg(List<String> areaAtuacaoEsg){
    //    List<ServicoEntity> servico = repository.findByAreaAtuacaoEsg(areaAtuacaoEsg);
    //    if(servico.isEmpty()){
    //        throw new ServicoException(String.format("O serviço com a área de atuação %s não existe", areaAtuacaoEsg));
    //    }
    //    return servico.stream().map(ServicoResponse::new).toList();
    //}

    public List<ServicoResponse> getServicoByAreaAtuacaoEsg(String areaAtuacaoEsg){
        List<ServicoEntity> servico = repository.findByAreaAtuacaoEsg(areaAtuacaoEsg);
        if(servico.isEmpty()){
            throw new ServicoException(String.format("O serviço com a área de atuação %s não existe", areaAtuacaoEsg));
        }
        return servico.stream().map(ServicoResponse::new).toList();
    }

    public List<ServicoResponse> getServicoByFkPrestadoraServico(UUID fkPrestadoraServico){
        List<ServicoEntity> listaServicoPorEmpresa = repository.findByFkPrestadoraServico(fkPrestadoraServico);
        if (listaServicoPorEmpresa.isEmpty()){
            throw new ServicoException("Não foram encontrados serviços com essa fkEmpresa");
        }
        return listaServicoPorEmpresa.stream().map(ServicoResponse::new).toList();
    }

    public ServicoResponse putServico(UUID id, ServicoRequest request) {
        Optional<ServicoEntity> servicoAtualizado = repository.findById(id);

        if (servicoAtualizado.isEmpty()) {
            throw new ServicoNotFoundException(String.format("Serviço não encontrado."));
        }

        ServicoEntity entity = servicoAtualizado.get();

        if (!request.nomeServico().isEmpty()) {
            entity.setNome(request.nomeServico());
        }

        if (!request.descricao().isEmpty()) {
            entity.setDescricao(request.descricao());
        }

        if (request.valor() > 0) {
            entity.setValor(request.valor());
        }

        if (!request.areaAtuacaoEsg().isEmpty()) {
        //    List<AreaAtuacaoEsgEnum> areaAtuacaoEsgList = request.areaAtuacaoEsg().stream().toList();
        //    entity.setAreaAtuacaoEsg(areaAtuacaoEsgList);
            entity.setAreaAtuacaoEsg(request.areaAtuacaoEsg());
        }

        repository.save(entity);
        return new ServicoResponse(entity);
    }

    public void deleteServico(UUID id){
        repository.deleteById(id);
    }

    public List<ServicoResponse> filaServico(ServicoResponse servicoResponse){
        List<ServicoResponse> filaServicoResponse = new ArrayList<>();

        if (!filaObj.isFull()){
            filaServicoResponse.add(filaObj.insert(servicoResponse));
        } else {
            filaServicoResponse.remove(filaObj.poll());
        }
        return filaServicoResponse.stream().toList();
    }

    public static class ServicoConfig {
        @Bean
        public ServicoFilaObj servicoFilaObj() {
            return new ServicoFilaObj();
        }
    }
}
