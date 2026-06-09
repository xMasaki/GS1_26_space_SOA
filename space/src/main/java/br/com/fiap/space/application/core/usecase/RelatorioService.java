package br.com.fiap.space.application.core.usecase;

import br.com.fiap.space.adapter.in.controller.response.relatorio.DadosRelatorioEstufa;
import br.com.fiap.space.application.core.domain.enums.NivelAlerta;
import br.com.fiap.space.application.core.domain.enums.TipoSensor;
import br.com.fiap.space.application.core.domain.model.Estufa;
import br.com.fiap.space.application.core.domain.model.LeituraSensor;
import br.com.fiap.space.application.port.out.EstufaRepository;
import br.com.fiap.space.application.port.out.LeituraSensorRepository;
import br.com.fiap.space.application.port.out.SensorRepository;
import br.com.fiap.space.exception.type.EstufaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private LeituraSensorRepository leituraSensorRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private EstufaRepository estufaRepository;

    public DadosRelatorioEstufa gerarRelatorio(Long estufaId, LocalDateTime inicio, LocalDateTime fim) {
        Estufa estufa = estufaRepository.findById(estufaId)
                .orElseThrow(() -> new EstufaNotFoundException(estufaId));

        LocalDateTime dataInicio = inicio != null ? inicio : LocalDateTime.now().minusDays(30);
        LocalDateTime dataFim = fim != null ? fim : LocalDateTime.now();

        List<LeituraSensor> leituras = leituraSensorRepository
                .findAllByEstufaIdAndDataRegistroBetween(estufaId, dataInicio, dataFim, PageRequest.of(0, Integer.MAX_VALUE))
                .getContent();

        Map<TipoSensor, Double> mediasPorSensor = calcularMediasPorSensor(leituras, estufaId);
        Map<NivelAlerta, Long> alertasPorNivel = contarAlertasPorNivel(leituras);

        return new DadosRelatorioEstufa(
                estufa.getId(),
                estufa.getNome(),
                dataInicio,
                dataFim,
                leituras.size(),
                mediasPorSensor,
                alertasPorNivel
        );
    }

    private Map<TipoSensor, Double> calcularMediasPorSensor(List<LeituraSensor> leituras, Long estufaId) {
        return sensorRepository.findAllByEstufaIdAndAtivoTrue(estufaId, PageRequest.of(0, Integer.MAX_VALUE))
                .getContent()
                .stream()
                .collect(Collectors.toMap(
                        sensor -> sensor.getTipo(),
                        sensor -> leituras.stream()
                                .filter(l -> l.getSensorId().equals(sensor.getId()))
                                .mapToDouble(LeituraSensor::getValor)
                                .average()
                                .orElse(0.0)
                ));
    }

    private Map<NivelAlerta, Long> contarAlertasPorNivel(List<LeituraSensor> leituras) {
        return leituras.stream()
                .collect(Collectors.groupingBy(LeituraSensor::getNivelAlerta, Collectors.counting()));
    }
}
