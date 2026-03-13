package io.github.erissonteixeira.api_weather.service.serviceimpl;

import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosResponseDto;
import io.github.erissonteixeira.api_weather.entity.DadosMeteorologicos;
import io.github.erissonteixeira.api_weather.exception.NegocioException;
import io.github.erissonteixeira.api_weather.exception.RecursoNaoEncontradoException;
import io.github.erissonteixeira.api_weather.mapper.DadosMeteorologicosMapper;
import io.github.erissonteixeira.api_weather.repository.DadosMeteorologicosRepository;
import io.github.erissonteixeira.api_weather.service.DadosMeteorologicosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosMeteorologicosServiceImpl implements DadosMeteorologicosService {

    private final DadosMeteorologicosRepository repository;
    private final DadosMeteorologicosMapper mapper;

    @Override
    public DadosMeteorologicosResponseDto salvar(DadosMeteorologicosRequestDto dto) {
        validarDuplicidade(dto);

        DadosMeteorologicos entidade = mapper.paraEntidade(dto);
        DadosMeteorologicos salvo = repository.save(entidade);

        return mapper.paraResponseDto(salvo);
    }

    @Override
    public List<DadosMeteorologicosResponseDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::paraResponseDto)
                .toList();
    }

    @Override
    public DadosMeteorologicosResponseDto buscarPorId(Long id) {
        DadosMeteorologicos entidade = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Dados meteorológicos não encontrados para o id informado."));

        return mapper.paraResponseDto(entidade);
    }

    @Override
    public DadosMeteorologicosResponseDto atualizar(Long id, DadosMeteorologicosRequestDto dto) {
        DadosMeteorologicos entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Dados meteorológicos não encontrados para o id informado."));

        repository.findByCidadeAndDataPrevisao(dto.getCidade(), dto.getDataPrevisao())
                .filter(dado -> !dado.getId().equals(id))
                .ifPresent(dado -> {
                    throw new NegocioException("Já existe um registro para a cidade e data informadas.");
                });

        entidadeExistente.setCidade(dto.getCidade());
        entidadeExistente.setDataPrevisao(dto.getDataPrevisao());
        entidadeExistente.setTempoDia(dto.getTempoDia());
        entidadeExistente.setTempoNoite(dto.getTempoNoite());
        entidadeExistente.setTemperaturaMaxima(dto.getTemperaturaMaxima());
        entidadeExistente.setTemperaturaMinima(dto.getTemperaturaMinima());
        entidadeExistente.setPrecipitacao(dto.getPrecipitacao());
        entidadeExistente.setHumidade(dto.getHumidade());
        entidadeExistente.setVelocidadeVento(dto.getVelocidadeVento());

        DadosMeteorologicos atualizado = repository.save(entidadeExistente);

        return mapper.paraResponseDto(atualizado);
    }

    @Override
    public void excluir(Long id) {
        DadosMeteorologicos entidade = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Dados meteorológicos não encontrados para o id informado."));

        repository.delete(entidade);
    }

    private void validarDuplicidade(DadosMeteorologicosRequestDto dto) {
        repository.findByCidadeAndDataPrevisao(dto.getCidade(), dto.getDataPrevisao())
                .ifPresent(dado -> {
                    throw new NegocioException("Já existe um registro para a cidade e data informadas.");
                });
    }
}