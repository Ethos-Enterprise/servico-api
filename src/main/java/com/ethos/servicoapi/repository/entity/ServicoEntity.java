package com.ethos.servicoapi.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Getter
@Table(name = "SERVICO")
@Entity
@Immutable
public class ServicoEntity {
    @Id
    Integer id;

    String nomeServico;

    String descricao;

    Double valor;

    public ServicoEntity() {
    }

    @Builder(toBuilder = true)
    public ServicoEntity(Integer id, String nomeServico, String descricao, Double valor) {
        this.id = id;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
