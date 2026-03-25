package io.github.erissonteixeira.api_weather.controller;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosResponseDto;
import io.github.erissonteixeira.api_weather.service.DadosMeteorologicosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosMeteorologicosResponseDto>> listar(
            @RequestParam(required = false) String cidade,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        return ResponseEntity.ok(service.listar(cidade, pagina, tamanho));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosMeteorologicosResponseDto> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/previsao-hoje")
    public ResponseEntity<DadosMeteorologicosResponseDto> previsaoHoje(
            @RequestParam String cidade
    ) {
        return ResponseEntity.ok(service.buscarPrevisaoHoje(cidade));
    }

    @GetMapping("/previsao-proximos-7-dias")
    public ResponseEntity<List<DadosMeteorologicosResponseDto>> previsao7Dias(
            @RequestParam String cidade
    ) {
        return ResponseEntity.ok(service.buscarPrevisaoProximos7Dias(cidade));
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