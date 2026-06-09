package br.com.fiap.space.adapter.in.controller.request.estufa;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEstufa(
        @NotBlank String nome,
        @NotBlank String codigo,
        @NotBlank String localizacao,
        String descricao,
        Double latitudeMarte,
        Double longitudeMarte
) {}
