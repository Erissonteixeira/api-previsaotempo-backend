package io.github.erissonteixeira.api_weather.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosMeteorologicosResponseDto {

    private Long id;
    private String cidade;
    private LocalDate dataPrevisao;
    private String tempoDia;
    private String tempoNoite;
    private BigDecimal temperaturaMaxima;
    private BigDecimal temperaturaMinima;
    private BigDecimal precipitacao;
    private BigDecimal humidade;
    private BigDecimal velocidadeVento;
}