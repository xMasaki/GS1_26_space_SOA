package br.com.fiap.space.adapter.in.controller;

import br.com.fiap.space.adapter.in.controller.response.historico.DadosHistoricoSensor;
import br.com.fiap.space.application.core.usecase.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/{sensorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<DadosHistoricoSensor> buscarHistorico(
            @PathVariable Long sensorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @PageableDefault(size = 20, sort = {"dataRegistro"}) Pageable paginacao) {
        DadosHistoricoSensor historico = historicoService.buscarHistorico(sensorId, inicio, fim, paginacao);
        return ResponseEntity.ok(historico);
    }
}
