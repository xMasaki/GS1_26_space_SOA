package br.com.fiap.space.application.core.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoSensor {

    TEMPERATURA("°C", -100.0, 200.0),
    UMIDADE("%", 0.0, 100.0),
    CO2("ppm", 0.0, 5000.0),
    O2("%", 0.0, 100.0),
    RADIACAO("mSv/h", 0.0, 1000.0),
    PH_AGUA("pH", 0.0, 14.0),
    LUMINOSIDADE("lux", 0.0, 200000.0);

    private final String unidade;
    private final double limiteMinFisico;
    private final double limiteMaxFisico;

    public boolean isValorNoIntervalFisico(Double valor) {
        return valor != null && valor >= limiteMinFisico && valor <= limiteMaxFisico;
    }
}
