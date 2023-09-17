package com.ethos.servicoapi.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Table(name = "SERVICO")
@Entity
@Immutable
public class ServicoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nome;

    String descricao;

    Double valor;

    public ServicoEntity() {
    }

    @Builder(toBuilder = true)
    public ServicoEntity(Integer id, String nome, String descricao, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
