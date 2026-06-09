package br.com.fiap.space.adapter.in.controller.response.sensor;

import br.com.fiap.space.application.core.domain.enums.TipoSensor;
import br.com.fiap.space.application.core.domain.model.Sensor;

public record DadosListagemSensor(
        Long id,
        String nome,
        String identificador,
        TipoSensor tipo,
        Long estufaId
) {
    public DadosListagemSensor(Sensor s) {
        this(s.getId(), s.getNome(), s.getIdentificador(), s.getTipo(), s.getEstufaId());
    }
}
