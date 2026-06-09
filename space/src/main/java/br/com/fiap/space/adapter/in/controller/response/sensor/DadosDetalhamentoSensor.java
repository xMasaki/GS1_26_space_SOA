package br.com.fiap.space.adapter.in.controller.response.sensor;

import br.com.fiap.space.application.core.domain.enums.TipoSensor;
import br.com.fiap.space.application.core.domain.model.Sensor;

import java.time.LocalDateTime;

public record DadosDetalhamentoSensor(
        Long id,
        String nome,
        String identificador,
        TipoSensor tipo,
        String localizacao,
        Long estufaId,
        Double limiteInferiorAviso,
        Double limiteSuperiorAviso,
        Double limiteInferiorCritico,
        Double limiteSuperiorCritico,
        LocalDateTime ultimaLeitura
) {
    public DadosDetalhamentoSensor(Sensor s) {
        this(s.getId(), s.getNome(), s.getIdentificador(), s.getTipo(), s.getLocalizacao(),
                s.getEstufaId(), s.getLimiteInferiorAviso(), s.getLimiteSuperiorAviso(),
                s.getLimiteInferiorCritico(), s.getLimiteSuperiorCritico(), s.getUltimaLeitura());
    }
}
