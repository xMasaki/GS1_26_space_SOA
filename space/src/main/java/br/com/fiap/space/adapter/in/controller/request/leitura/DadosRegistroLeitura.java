package br.com.fiap.space.adapter.in.controller.request.leitura;

import jakarta.validation.constraints.NotNull;

public record DadosRegistroLeitura(
        @NotNull Long idSensor,
        @NotNull Double valor
) {}
