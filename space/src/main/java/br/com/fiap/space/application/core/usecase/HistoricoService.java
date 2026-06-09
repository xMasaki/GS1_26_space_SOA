package br.com.fiap.space.application.core.usecase;

import br.com.fiap.space.adapter.in.controller.response.historico.DadosHistoricoSensor;
import br.com.fiap.space.adapter.in.controller.response.leitura.DadosDetalhamentoLeitura;
import br.com.fiap.space.application.core.domain.model.Sensor;
import br.com.fiap.space.application.port.out.LeituraSensorRepository;
import br.com.fiap.space.application.port.out.SensorRepository;
import br.com.fiap.space.exception.type.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoricoService {

    @Autowired
    private LeituraSensorRepository leituraSensorRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public DadosHistoricoSensor buscarHistorico(Long sensorId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException(sensorId));

        Page<DadosDetalhamentoLeitura> leituras;

        if (inicio != null && fim != null) {
            leituras = leituraSensorRepository
                    .findAllBySensorIdAndDataRegistroBetween(sensorId, inicio, fim, pageable)
                    .map(DadosDetalhamentoLeitura::new);
        } else {
            leituras = leituraSensorRepository
                    .findAllBySensorId(sensorId, pageable)
                    .map(DadosDetalhamentoLeitura::new);
        }

        return new DadosHistoricoSensor(
                sensor.getId(),
                sensor.getNome(),
                sensor.getTipo(),
                leituras,
                leituras.getTotalElements()
        );
    }
}
