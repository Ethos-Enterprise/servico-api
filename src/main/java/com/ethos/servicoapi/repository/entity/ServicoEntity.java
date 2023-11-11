package com.ethos.servicoapi.repository.entity;

import com.ethos.servicoapi.repository.entity.esgenum.AreaAtuacaoEsgEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String nome;

    String descricao;

    Double valor;

    // @ElementCollection
    // @Column(name = "area_atuacao_esg")
    List<String> areaAtuacaoEsg;

    UUID fkPrestadoraServico;
}
