package br.com.fiap.space.application.core.validation.leitura;

import br.com.fiap.space.adapter.in.controller.request.leitura.DadosRegistroLeitura;
import br.com.fiap.space.application.core.validation.interfaces.ValidacoesDeRegistroLeitura;
import br.com.fiap.space.application.port.out.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorValorNoIntervalFisico implements ValidacoesDeRegistroLeitura {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public Optional<String> validar(DadosRegistroLeitura dados) {
        return sensorRepository.findById(dados.idSensor())
                .filter(s -> !s.getTipo().isValorNoIntervalFisico(dados.valor()))
                .map(s -> String.format(
                        "Valor %.2f %s fora do intervalo físico possível para sensor do tipo %s (%.1f – %.1f)",
                        dados.valor(), s.getTipo().getUnidade(),
                        s.getTipo().name(),
                        s.getTipo().getLimiteMinFisico(),
                        s.getTipo().getLimiteMaxFisico()
                ));
    }
}
