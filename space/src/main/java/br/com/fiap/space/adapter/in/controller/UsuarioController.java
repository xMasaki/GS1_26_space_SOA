package br.com.fiap.space.adapter.in.controller;

import br.com.fiap.space.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.fiap.space.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.fiap.space.application.core.domain.model.Usuario;
import br.com.fiap.space.application.port.out.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder) {
        String senhaCodificada = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados, senhaCodificada);
        usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(
            @PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        Page<DadosDetalhamentoUsuario> page = usuarioRepository.findAll(paginacao)
                .map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(page);
    }
}
