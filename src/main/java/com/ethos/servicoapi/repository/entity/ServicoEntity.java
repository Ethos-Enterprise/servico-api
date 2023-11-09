package com.ethos.servicoapi.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SERVICO")
@Entity
@Immutable
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    String nome;

    String descricao;

    Double valor;

    String areaAtuacaoEsg;

    UUID fkPrestadoraServico;
}
