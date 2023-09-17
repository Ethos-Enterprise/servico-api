package com.ethos.servicoapi.controller.request;

import jakarta.validation.constraints.*;

public record ServicoRequest(
        @NotBlank(message = "O nome do serviço não pode estar em branco.")
        @Size(min = 5, max = 60, message = "O nome do serviço deve ter entre 5 e 60 caracteres.")
        String nomeServico,

        @NotBlank(message = "A descrição não pode estar em branco.")
        @Size(min = 5, max = 250, message = "A descrição deve ter entre 5 e 250 caracteres.")
        String descricao,

        @NotNull(message = "O valor não pode ser nulo.")
        @Positive(message = "O valor deve ser um número positivo.")
        Double valor) {
}
