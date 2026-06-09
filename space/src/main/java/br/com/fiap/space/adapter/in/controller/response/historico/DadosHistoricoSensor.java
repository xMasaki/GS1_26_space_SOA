package br.com.fiap.space.adapter.in.controller.response.historico;

import br.com.fiap.space.adapter.in.controller.response.leitura.DadosDetalhamentoLeitura;
import br.com.fiap.space.application.core.domain.enums.TipoSensor;
import org.springframework.data.domain.Page;

public record DadosHistoricoSensor(
        Long sensorId,
        String nomeSensor,
        TipoSensor tipo,
        Page<DadosDetalhamentoLeitura> leituras,
        long totalLeituras
) {}
