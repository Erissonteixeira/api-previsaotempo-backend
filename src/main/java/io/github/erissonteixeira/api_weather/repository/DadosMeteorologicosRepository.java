package io.github.erissonteixeira.api_weather.repository;

import io.github.erissonteixeira.api_weather.entity.DadosMeteorologicos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DadosMeteorologicosRepository extends JpaRepository<DadosMeteorologicos, Long> {

    Optional<DadosMeteorologicos> findByCidadeAndDataPrevisao(String cidade, LocalDate dataPrevisao);

    Page<DadosMeteorologicos> findByDataPrevisaoGreaterThanEqual(LocalDate dataPrevisao, Pageable pageable);

    Page<DadosMeteorologicos> findByCidadeIgnoreCaseAndDataPrevisaoGreaterThanEqual(
            String cidade,
            LocalDate dataPrevisao,
            Pageable pageable
    );

    Optional<DadosMeteorologicos> findByCidadeIgnoreCaseAndDataPrevisao(
            String cidade,
            LocalDate dataPrevisao
    );

    List<DadosMeteorologicos> findByCidadeIgnoreCaseAndDataPrevisaoBetweenOrderByDataPrevisaoAsc(
            String cidade,
            LocalDate dataInicial,
            LocalDate dataFinal
    );
}