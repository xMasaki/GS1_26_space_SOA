package br.com.fiap.space.application.core.usecase;

import br.com.fiap.space.adapter.in.controller.request.leitura.DadosRegistroLeitura;
import br.com.fiap.space.adapter.in.controller.response.leitura.DadosDetalhamentoLeitura;
import br.com.fiap.space.application.core.domain.enums.NivelAlerta;
import br.com.fiap.space.application.core.domain.model.LeituraSensor;
import br.com.fiap.space.application.core.domain.model.Sensor;
import br.com.fiap.space.application.core.validation.interfaces.ValidacoesDeRegistroLeitura;
import br.com.fiap.space.application.port.out.LeituraSensorRepository;
import br.com.fiap.space.application.port.out.SensorRepository;
import br.com.fiap.space.exception.type.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroLeituraService {

    @Autowired
    private LeituraSensorRepository leituraSensorRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private List<ValidacoesDeRegistroLeitura> validadores;

    @Transactional
    public DadosDetalhamentoLeitura registrar(DadosRegistroLeitura dados, Long usuarioId) {
        Sensor sensor = sensorRepository.findById(dados.idSensor())
                .orElseThrow(() -> new SensorNotFoundException(dados.idSensor()));

        String alertas = validadores.stream()
                .map(v -> v.validar(dados))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining(" | "),
                        s -> s.isEmpty() ? null : s
                ));

        NivelAlerta nivelAlerta = sensor.calcularNivelAlerta(dados.valor());

        LeituraSensor leitura = new LeituraSensor(
                sensor.getId(),
                sensor.getEstufaId(),
                dados.valor(),
                sensor.getTipo().getUnidade(),
                nivelAlerta,
                alertas,
                usuarioId
        );
        leituraSensorRepository.save(leitura);

        sensor.registrarLeitura();
        sensorRepository.save(sensor);

        return new DadosDetalhamentoLeitura(leitura);
    }
}
