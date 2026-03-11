package io.github.erissonteixeira.api_weather.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "dados_meteorologicos",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_dados_meteorologicos_cidade_data",
                        columnNames = {"cidade", "data_previsao"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosMeteorologicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cidade", nullable = false, length = 150)
    private String cidade;

    @Column(name = "data_previsao", nullable = false)
    private LocalDate dataPrevisao;

    @Column(name = "tempo_dia", nullable = false, length = 50)
    private String tempoDia;

    @Column(name = "tempo_noite", nullable = false, length = 50)
    private String tempoNoite;

    @Column(name = "temperatura_maxima", nullable = false, precision = 5, scale = 2)
    private BigDecimal temperaturaMaxima;

    @Column(name = "temperatura_minima", nullable = false, precision = 5, scale = 2)
    private BigDecimal temperaturaMinima;

    @Column(name = "precipitacao", nullable = false, precision = 5, scale = 2)
    private BigDecimal precipitacao;

    @Column(name = "humidade", nullable = false, precision = 5, scale = 2)
    private BigDecimal humidade;

    @Column(name = "velocidade_vento", nullable = false, precision = 5, scale = 2)
    private BigDecimal velocidadeVento;
}