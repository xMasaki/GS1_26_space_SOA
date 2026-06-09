package br.com.fiap.space.adapter.in.controller.request.usuario;

import br.com.fiap.space.application.core.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull Role role
) {}
