package br.com.fiap.space.adapter.in.controller.response.leitura;

import br.com.fiap.space.application.core.domain.enums.NivelAlerta;
import br.com.fiap.space.application.core.domain.model.LeituraSensor;

import java.time.LocalDateTime;

public record DadosDetalhamentoLeitura(
        Long id,
        Long sensorId,
        Long estufaId,
        LocalDateTime dataRegistro,
        Double valor,
        String unidade,
        NivelAlerta nivelAlerta,
        String alertas
) {
    public DadosDetalhamentoLeitura(LeituraSensor l) {
        this(l.getId(), l.getSensorId(), l.getEstufaId(), l.getDataRegistro(),
                l.getValor(), l.getUnidade(), l.getNivelAlerta(), l.getAlertas());
    }
}
