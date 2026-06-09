package br.com.fiap.space.application.core.domain.model;

import br.com.fiap.space.adapter.in.controller.request.estufa.DadosAtualizacaoEstufa;
import br.com.fiap.space.adapter.in.controller.request.estufa.DadosCadastroEstufa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estufas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estufa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;
    private String nome;
    private String codigo;
    private String localizacao;
    private String descricao;

    @Column(name = "latitude_marte")
    private Double latitudeMarte;

    @Column(name = "longitude_marte")
    private Double longitudeMarte;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public Estufa(DadosCadastroEstufa dados, Long usuarioId) {
        this.ativo = true;
        this.nome = dados.nome();
        this.codigo = dados.codigo();
        this.localizacao = dados.localizacao();
        this.descricao = dados.descricao();
        this.latitudeMarte = dados.latitudeMarte();
        this.longitudeMarte = dados.longitudeMarte();
        this.usuarioId = usuarioId;
    }

    public void atualizarInformacoes(DadosAtualizacaoEstufa dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.localizacao() != null) this.localizacao = dados.localizacao();
        if (dados.descricao() != null) this.descricao = dados.descricao();
    }

    public void excluir() {
        this.ativo = false;
    }
}
