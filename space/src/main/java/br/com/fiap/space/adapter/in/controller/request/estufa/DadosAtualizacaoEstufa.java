package br.com.fiap.space.adapter.in.controller.request.estufa;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEstufa(
        @NotNull Long id,
        String nome,
        String localizacao,
        String descricao
) {}
