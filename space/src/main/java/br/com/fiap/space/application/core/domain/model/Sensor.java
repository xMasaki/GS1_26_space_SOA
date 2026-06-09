package br.com.fiap.space.application.core.domain.model;

import br.com.fiap.space.adapter.in.controller.request.sensor.DadosAtualizacaoSensor;
import br.com.fiap.space.adapter.in.controller.request.sensor.DadosCadastroSensor;
import br.com.fiap.space.application.core.domain.enums.NivelAlerta;
import br.com.fiap.space.application.core.domain.enums.TipoSensor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;
    private String nome;
    private String identificador;

    @Enumerated(EnumType.STRING)
    private TipoSensor tipo;

    private String localizacao;

    @Column(name = "estufa_id")
    private Long estufaId;

    @Column(name = "limite_inferior_aviso")
    private Double limiteInferiorAviso;

    @Column(name = "limite_superior_aviso")
    private Double limiteSuperiorAviso;

    @Column(name = "limite_inferior_critico")
    private Double limiteInferiorCritico;

    @Column(name = "limite_superior_critico")
    private Double limiteSuperiorCritico;

    @Column(name = "ultima_leitura")
    private LocalDateTime ultimaLeitura;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public Sensor(DadosCadastroSensor dados, Long usuarioId) {
        this.ativo = true;
        this.nome = dados.nome();
        this.identificador = dados.identificador();
        this.tipo = dados.tipo();
        this.localizacao = dados.localizacao();
        this.estufaId = dados.estufaId();
        this.limiteInferiorAviso = dados.limiteInferiorAviso();
        this.limiteSuperiorAviso = dados.limiteSuperiorAviso();
        this.limiteInferiorCritico = dados.limiteInferiorCritico();
        this.limiteSuperiorCritico = dados.limiteSuperiorCritico();
        this.usuarioId = usuarioId;
    }

    public void atualizarInformacoes(DadosAtualizacaoSensor dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.localizacao() != null) this.localizacao = dados.localizacao();
        if (dados.limiteInferiorAviso() != null) this.limiteInferiorAviso = dados.limiteInferiorAviso();
        if (dados.limiteSuperiorAviso() != null) this.limiteSuperiorAviso = dados.limiteSuperiorAviso();
        if (dados.limiteInferiorCritico() != null) this.limiteInferiorCritico = dados.limiteInferiorCritico();
        if (dados.limiteSuperiorCritico() != null) this.limiteSuperiorCritico = dados.limiteSuperiorCritico();
    }

    public void excluir() {
        this.ativo = false;
    }

    public void registrarLeitura() {
        this.ultimaLeitura = LocalDateTime.now();
    }

    public NivelAlerta calcularNivelAlerta(Double valor) {
        if (!tipo.isValorNoIntervalFisico(valor)) return NivelAlerta.EMERGENCIA;
        if (limiteInferiorCritico != null && valor < limiteInferiorCritico) return NivelAlerta.CRITICO;
        if (limiteSuperiorCritico != null && valor > limiteSuperiorCritico) return NivelAlerta.CRITICO;
        if (limiteInferiorAviso != null && valor < limiteInferiorAviso) return NivelAlerta.AVISO;
        if (limiteSuperiorAviso != null && valor > limiteSuperiorAviso) return NivelAlerta.AVISO;
        return NivelAlerta.INFO;
    }
}
