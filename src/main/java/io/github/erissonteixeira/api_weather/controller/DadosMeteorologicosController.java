package io.github.erissonteixeira.api_weather.controller;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosResponseDto;
import io.github.erissonteixeira.api_weather.service.DadosMeteorologicosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados-meteorologicos")
@RequiredArgsConstructor
public class DadosMeteorologicosController {

    private final DadosMeteorologicosService service;

    @PostMapping
    public ResponseEntity<DadosMeteorologicosResponseDto> salvar(
            @Valid @RequestBody DadosMeteorologicosRequestDto dto
    ) {
        DadosMeteorologicosResponseDto response = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DadosMeteorologicosResponseDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosMeteorologicosResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosMeteorologicosResponseDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DadosMeteorologicosRequestDto dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}