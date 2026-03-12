package io.github.erissonteixeira.api_weather.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosMeteorologicosRequestDto {

    @NotBlank(message = "O campo cidade é obrigatório.")
    private String cidade;

    @NotNull(message = "O campo data da previsão é obrigatório.")
    private LocalDate dataPrevisao;

    @NotBlank(message = "O campo tempo do dia é obrigatório.")
    private String tempoDia;

    @NotBlank(message = "O campo tempo da noite é obrigatório.")
    private String tempoNoite;

    @NotNull(message = "O campo temperatura máxima é obrigatório.")
    @DecimalMin(value = "-100.0", message = "A temperatura máxima deve ser maior ou igual a -100.")
    private BigDecimal temperaturaMaxima;

    @NotNull(message = "O campo temperatura mínima é obrigatório.")
    @DecimalMin(value = "-100.0", message = "A temperatura mínima deve ser maior ou igual a -100.")
    private BigDecimal temperaturaMinima;

    @NotNull(message = "O campo precipitação é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = true, message = "A precipitação deve ser maior ou igual a 0.")
    private BigDecimal precipitacao;

    @NotNull(message = "O campo humidade é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = true, message = "A humidade deve ser maior ou igual a 0.")
    private BigDecimal humidade;

    @NotNull(message = "O campo velocidade do vento é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = true, message = "A velocidade do vento deve ser maior ou igual a 0.")
    private BigDecimal velocidadeVento;
}