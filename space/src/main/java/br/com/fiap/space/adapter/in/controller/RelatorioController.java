package br.com.fiap.space.adapter.in.controller;

import br.com.fiap.space.adapter.in.controller.response.relatorio.DadosRelatorioEstufa;
import br.com.fiap.space.application.core.usecase.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/{estufaId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<DadosRelatorioEstufa> gerarRelatorio(
            @PathVariable Long estufaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        DadosRelatorioEstufa relatorio = relatorioService.gerarRelatorio(estufaId, inicio, fim);
        return ResponseEntity.ok(relatorio);
    }
}
