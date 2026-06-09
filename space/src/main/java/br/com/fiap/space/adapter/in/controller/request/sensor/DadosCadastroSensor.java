package br.com.fiap.space.adapter.in.controller.request.sensor;

import br.com.fiap.space.application.core.domain.enums.TipoSensor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroSensor(
        @NotBlank String nome,
        @NotBlank String identificador,
        @NotNull TipoSensor tipo,
        String localizacao,
        @NotNull Long estufaId,
        Double limiteInferiorAviso,
        Double limiteSuperiorAviso,
        Double limiteInferiorCritico,
        Double limiteSuperiorCritico
) {}
