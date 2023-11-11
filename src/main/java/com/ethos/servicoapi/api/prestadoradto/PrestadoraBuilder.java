package com.ethos.servicoapi.api.prestadoradto;

import java.util.UUID;

public class PrestadoraBuilder {
    private UUID id;

    private String statusAprovacao;

    public PrestadoraBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public PrestadoraBuilder setStatusAprovacao(String statusAprovacao){
        this.statusAprovacao = statusAprovacao;
        return this;
    }

    public Prestadora createPrestadora() {
        return new Prestadora(id, statusAprovacao);
    }
}
