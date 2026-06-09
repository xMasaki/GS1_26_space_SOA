package br.com.fiap.space.adapter.in.controller;

import br.com.fiap.space.adapter.in.controller.request.leitura.DadosRegistroLeitura;
import br.com.fiap.space.adapter.in.controller.response.leitura.DadosDetalhamentoLeitura;
import br.com.fiap.space.application.core.domain.model.Usuario;
import br.com.fiap.space.application.core.usecase.RegistroLeituraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registros/leituras")
public class LeituraSensorController {

    @Autowired
    private RegistroLeituraService registroLeituraService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CIENTISTA', 'OPERADOR')")
    public ResponseEntity<DadosDetalhamentoLeitura> registrar(
            @RequestBody @Valid DadosRegistroLeitura dados,
            @AuthenticationPrincipal Usuario usuario,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoLeitura leitura = registroLeituraService.registrar(dados, usuario.getId());
        URI uri = uriBuilder.path("/registros/leituras/{id}").buildAndExpand(leitura.id()).toUri();
        return ResponseEntity.created(uri).body(leitura);
    }
}
