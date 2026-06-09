package br.com.fiap.space.application.core.domain.model;

import br.com.fiap.space.application.core.domain.enums.NivelAlerta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "leituras_sensor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_id")
    private Long sensorId;

    @Column(name = "estufa_id")
    private Long estufaId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    private Double valor;
    private String unidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_alerta")
    private NivelAlerta nivelAlerta;

    @Column(length = 1000)
    private String alertas;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public LeituraSensor(Long sensorId, Long estufaId, Double valor, String unidade,
                         NivelAlerta nivelAlerta, String alertas, Long usuarioId) {
        this.sensorId = sensorId;
        this.estufaId = estufaId;
        this.dataRegistro = LocalDateTime.now();
        this.valor = valor;
        this.unidade = unidade;
        this.nivelAlerta = nivelAlerta;
        this.alertas = alertas;
        this.usuarioId = usuarioId;
    }
}
