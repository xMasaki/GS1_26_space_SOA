package br.com.fiap.space.adapter.in.controller;

import br.com.fiap.space.adapter.in.controller.request.estufa.DadosAtualizacaoEstufa;
import br.com.fiap.space.adapter.in.controller.request.estufa.DadosCadastroEstufa;
import br.com.fiap.space.adapter.in.controller.response.estufa.DadosDetalhamentoEstufa;
import br.com.fiap.space.adapter.in.controller.response.estufa.DadosListagemEstufa;
import br.com.fiap.space.application.core.domain.model.Estufa;
import br.com.fiap.space.application.core.domain.model.Usuario;
import br.com.fiap.space.application.port.out.EstufaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/estufas")
public class EstufaController {

    @Autowired
    private EstufaRepository estufaRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoEstufa> cadastrar(
            @RequestBody @Valid DadosCadastroEstufa dados,
            @AuthenticationPrincipal Usuario usuario,
            UriComponentsBuilder uriBuilder) {
        Estufa estufa = new Estufa(dados, usuario.getId());
        estufaRepository.save(estufa);
        URI uri = uriBuilder.path("/estufas/{id}").buildAndExpand(estufa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEstufa(estufa));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<Page<DadosListagemEstufa>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemEstufa> page = estufaRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemEstufa::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<DadosDetalhamentoEstufa> detalhar(@PathVariable Long id) {
        Estufa estufa = estufaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEstufa(estufa));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<DadosDetalhamentoEstufa> atualizar(
            @RequestBody @Valid DadosAtualizacaoEstufa dados) {
        Estufa estufa = estufaRepository.getReferenceById(dados.id());
        estufa.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoEstufa(estufa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Estufa estufa = estufaRepository.getReferenceById(id);
        estufa.excluir();
        return ResponseEntity.noContent().build();
    }
}
