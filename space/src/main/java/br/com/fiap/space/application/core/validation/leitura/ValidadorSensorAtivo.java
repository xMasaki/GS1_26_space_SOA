package br.com.fiap.space.application.core.validation.leitura;

import br.com.fiap.space.adapter.in.controller.request.leitura.DadosRegistroLeitura;
import br.com.fiap.space.application.core.validation.interfaces.ValidacoesDeRegistroLeitura;
import br.com.fiap.space.application.port.out.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorSensorAtivo implements ValidacoesDeRegistroLeitura {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public Optional<String> validar(DadosRegistroLeitura dados) {
        boolean sensorInativo = sensorRepository.findById(dados.idSensor())
                .map(s -> !s.getAtivo())
                .orElse(false);
        return sensorInativo
                ? Optional.of("Sensor inativo — leitura registrada com alerta de monitoramento")
                : Optional.empty();
    }
}
