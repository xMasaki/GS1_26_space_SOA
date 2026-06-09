package br.com.fiap.space.adapter.in.controller.response.estufa;

import br.com.fiap.space.application.core.domain.model.Estufa;

public record DadosDetalhamentoEstufa(
        Long id,
        String nome,
        String codigo,
        String localizacao,
        String descricao,
        Double latitudeMarte,
        Double longitudeMarte
) {
    public DadosDetalhamentoEstufa(Estufa e) {
        this(e.getId(), e.getNome(), e.getCodigo(), e.getLocalizacao(),
                e.getDescricao(), e.getLatitudeMarte(), e.getLongitudeMarte());
    }
}
