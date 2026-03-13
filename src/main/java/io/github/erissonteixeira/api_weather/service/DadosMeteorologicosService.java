package io.github.erissonteixeira.api_weather.service;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DadosMeteorologicosService {

    DadosMeteorologicosResponseDto salvar(DadosMeteorologicosRequestDto dto);

    Page<DadosMeteorologicosResponseDto> listar(String cidade, int pagina, int tamanho);

    DadosMeteorologicosResponseDto buscarPorId(Long id);

    DadosMeteorologicosResponseDto buscarPrevisaoHoje(String cidade);

    List<DadosMeteorologicosResponseDto> buscarPrevisaoProximos7Dias(String cidade);

    DadosMeteorologicosResponseDto atualizar(Long id, DadosMeteorologicosRequestDto dto);

    void excluir(Long id);
}