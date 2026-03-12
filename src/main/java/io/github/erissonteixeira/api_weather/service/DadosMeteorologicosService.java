package io.github.erissonteixeira.api_weather.service;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosResponseDto;

import java.util.List;

public interface DadosMeteorologicosService {

    DadosMeteorologicosResponseDto salvar(DadosMeteorologicosRequestDto dto);

    List<DadosMeteorologicosResponseDto> listarTodos();

    DadosMeteorologicosResponseDto buscarPorId(Long id);

    DadosMeteorologicosResponseDto atualizar(Long id, DadosMeteorologicosRequestDto dto);

    void excluir(Long id);
}