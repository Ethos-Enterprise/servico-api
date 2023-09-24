package com.ethos.servicoapi.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@AllArgsConstructor
@Table(name = "SERVICO")
@Entity
@Immutable
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nome;

    String descricao;

    Double valor;

    String areaAtuacaoEsg;
}
