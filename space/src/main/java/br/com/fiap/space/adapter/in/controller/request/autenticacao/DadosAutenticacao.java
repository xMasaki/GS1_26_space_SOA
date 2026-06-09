package br.com.fiap.space.adapter.in.controller.request.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank String login,
        @NotBlank String senha
) {}
