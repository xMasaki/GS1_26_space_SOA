package br.com.fiap.space.adapter.in.controller.response.usuario;

import br.com.fiap.space.application.core.domain.enums.Role;
import br.com.fiap.space.application.core.domain.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        Role role
) {
    public DadosDetalhamentoUsuario(Usuario u) {
        this(u.getId(), u.getLogin(), u.getRole());
    }
}
