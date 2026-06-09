package br.com.fiap.space.adapter.in.controller.request.sensor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSensor(
        @NotNull Long id,
        String nome,
        String localizacao,
        Double limiteInferiorAviso,
        Double limiteSuperiorAviso,
        Double limiteInferiorCritico,
        Double limiteSuperiorCritico
) {}
