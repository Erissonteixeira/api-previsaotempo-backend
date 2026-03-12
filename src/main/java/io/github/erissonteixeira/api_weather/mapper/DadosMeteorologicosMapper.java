package io.github.erissonteixeira.api_weather.mapper;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosResponseDto;
import io.github.erissonteixeira.api_weather.entity.DadosMeteorologicos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DadosMeteorologicosMapper {

    DadosMeteorologicos paraEntidade(DadosMeteorologicosRequestDto dto);

    DadosMeteorologicosResponseDto paraResponseDto(DadosMeteorologicos entidade);
}