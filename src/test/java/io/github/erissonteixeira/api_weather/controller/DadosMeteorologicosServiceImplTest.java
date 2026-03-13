package io.github.erissonteixeira.api_weather.controller;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.entity.DadosMeteorologicos;
import io.github.erissonteixeira.api_weather.mapper.DadosMeteorologicosMapper;
import io.github.erissonteixeira.api_weather.repository.DadosMeteorologicosRepository;
import io.github.erissonteixeira.api_weather.service.serviceimpl.DadosMeteorologicosServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DadosMeteorologicosServiceImplTest {

    @Mock
    private DadosMeteorologicosRepository repository;

    @Mock
    private DadosMeteorologicosMapper mapper;

    @InjectMocks
    private DadosMeteorologicosServiceImpl service;

    @Test
    void deveSalvarDadosMeteorologicos() {

        DadosMeteorologicosRequestDto dto = DadosMeteorologicosRequestDto.builder()
                .cidade("Porto Alegre")
                .dataPrevisao(LocalDate.now())
                .tempoDia("Sol")
                .tempoNoite("Nublado")
                .temperaturaMaxima(BigDecimal.valueOf(30))
                .temperaturaMinima(BigDecimal.valueOf(20))
                .precipitacao(BigDecimal.valueOf(10))
                .humidade(BigDecimal.valueOf(70))
                .velocidadeVento(BigDecimal.valueOf(15))
                .build();

        DadosMeteorologicos entidade = DadosMeteorologicos.builder()
                .temperaturaMaxima(BigDecimal.valueOf(30))
                .temperaturaMinima(BigDecimal.valueOf(20))
                .build();

        when(repository.findByCidadeAndDataPrevisao(any(), any()))
                .thenReturn(Optional.empty());

        when(mapper.paraEntidade(any()))
                .thenReturn(entidade);

        when(repository.save(any()))
                .thenReturn(entidade);

        service.salvar(dto);

        verify(repository, times(1)).save(any());
    }
}