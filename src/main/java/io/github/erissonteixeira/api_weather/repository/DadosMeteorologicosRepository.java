package io.github.erissonteixeira.api_weather.repository;

import io.github.erissonteixeira.api_weather.entity.DadosMeteorologicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DadosMeteorologicosRepository extends JpaRepository<DadosMeteorologicos, Long> {

    Optional<DadosMeteorologicos> findByCidadeAndDataPrevisao(String cidade, LocalDate dataPrevisao);

}