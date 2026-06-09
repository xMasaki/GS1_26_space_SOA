package br.com.fiap.space.application.core.validation.interfaces;

import br.com.fiap.space.adapter.in.controller.request.leitura.DadosRegistroLeitura;

import java.util.Optional;

public interface ValidacoesDeRegistroLeitura {
    Optional<String> validar(DadosRegistroLeitura dados);
}
