package br.com.fiap.space.adapter.in.controller.response.relatorio;

import br.com.fiap.space.application.core.domain.enums.NivelAlerta;
import br.com.fiap.space.application.core.domain.enums.TipoSensor;

import java.time.LocalDateTime;
import java.util.Map;

public record DadosRelatorioEstufa(
        Long estufaId,
        String nomeEstufa,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        long totalLeituras,
        Map<TipoSensor, Double> mediasPorSensor,
        Map<NivelAlerta, Long> alertasPorNivel
) {}
