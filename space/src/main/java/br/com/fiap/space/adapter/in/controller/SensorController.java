package br.com.fiap.space.adapter.in.controller;

import br.com.fiap.space.adapter.in.controller.request.sensor.DadosAtualizacaoSensor;
import br.com.fiap.space.adapter.in.controller.request.sensor.DadosCadastroSensor;
import br.com.fiap.space.adapter.in.controller.response.sensor.DadosDetalhamentoSensor;
import br.com.fiap.space.adapter.in.controller.response.sensor.DadosListagemSensor;
import br.com.fiap.space.application.core.domain.model.Sensor;
import br.com.fiap.space.application.core.domain.model.Usuario;
import br.com.fiap.space.application.port.out.SensorRepository;
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
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<DadosDetalhamentoSensor> cadastrar(
            @RequestBody @Valid DadosCadastroSensor dados,
            @AuthenticationPrincipal Usuario usuario,
            UriComponentsBuilder uriBuilder) {
        Sensor sensor = new Sensor(dados, usuario.getId());
        sensorRepository.save(sensor);
        URI uri = uriBuilder.path("/sensores/{id}").buildAndExpand(sensor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoSensor(sensor));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<Page<DadosListagemSensor>> listar(
            @RequestParam(required = false) Long estufaId,
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemSensor> page;
        if (estufaId != null) {
            page = sensorRepository.findAllByEstufaIdAndAtivoTrue(estufaId, paginacao)
                    .map(DadosListagemSensor::new);
        } else {
            page = sensorRepository.findAllByAtivoTrue(paginacao)
                    .map(DadosListagemSensor::new);
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA', 'OPERADOR')")
    public ResponseEntity<DadosDetalhamentoSensor> detalhar(@PathVariable Long id) {
        Sensor sensor = sensorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoSensor(sensor));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'CIENTISTA')")
    public ResponseEntity<DadosDetalhamentoSensor> atualizar(
            @RequestBody @Valid DadosAtualizacaoSensor dados) {
        Sensor sensor = sensorRepository.getReferenceById(dados.id());
        sensor.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoSensor(sensor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Sensor sensor = sensorRepository.getReferenceById(id);
        sensor.excluir();
        return ResponseEntity.noContent().build();
    }
}
