package br.com.fiap.space.application.core.validation.leitura;

import br.com.fiap.space.adapter.in.controller.request.leitura.DadosRegistroLeitura;
import br.com.fiap.space.application.core.domain.model.Sensor;
import br.com.fiap.space.application.core.validation.interfaces.ValidacoesDeRegistroLeitura;
import br.com.fiap.space.application.port.out.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorLimitesCriticos implements ValidacoesDeRegistroLeitura {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public Optional<String> validar(DadosRegistroLeitura dados) {
        return sensorRepository.findById(dados.idSensor())
                .flatMap(s -> gerarAlerta(s, dados.valor()));
    }

    private Optional<String> gerarAlerta(Sensor sensor, Double valor) {
        if (sensor.getLimiteInferiorCritico() != null && valor < sensor.getLimiteInferiorCritico()) {
            return Optional.of(String.format(
                    "CRÍTICO: valor %.2f %s abaixo do limite crítico inferior (%.2f)",
                    valor, sensor.getTipo().getUnidade(), sensor.getLimiteInferiorCritico()
            ));
        }
        if (sensor.getLimiteSuperiorCritico() != null && valor > sensor.getLimiteSuperiorCritico()) {
            return Optional.of(String.format(
                    "CRÍTICO: valor %.2f %s acima do limite crítico superior (%.2f)",
                    valor, sensor.getTipo().getUnidade(), sensor.getLimiteSuperiorCritico()
            ));
        }
        return Optional.empty();
    }
}
