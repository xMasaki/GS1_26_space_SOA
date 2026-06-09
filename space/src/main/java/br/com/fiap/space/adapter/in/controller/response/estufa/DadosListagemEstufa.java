package br.com.fiap.space.adapter.in.controller.response.estufa;

import br.com.fiap.space.application.core.domain.model.Estufa;

public record DadosListagemEstufa(
        Long id,
        String nome,
        String codigo,
        String localizacao
) {
    public DadosListagemEstufa(Estufa e) {
        this(e.getId(), e.getNome(), e.getCodigo(), e.getLocalizacao());
    }
}
