package com.ethos.servicoapi.controller.request;

import jakarta.validation.constraints.*;

public record ServicoRequest(
        @NotBlank
        @Size(min = 5, max = 60)
        String nomeServico,

        @NotBlank
        @Size(min = 5, max = 250)
        String descricao,

        @NotNull @Positive
        Double valor) {
}