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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosMeteorologicosServiceImpl implements DadosMeteorologicosService {

    private final DadosMeteorologicosRepository repository;
    private final DadosMeteorologicosMapper mapper;

    @Override
    public DadosMeteorologicosResponseDto salvar(DadosMeteorologicosRequestDto dto) {

        repository.findByCidadeAndDataPrevisao(dto.getCidade(), dto.getDataPrevisao())
                .ifPresent(dado -> {
                    throw new NegocioException("Já existe um registro para a cidade e data informadas.");
                });

        DadosMeteorologicos entidade = mapper.paraEntidade(dto);

        if (entidade.getTemperaturaMaxima().compareTo(entidade.getTemperaturaMinima()) < 0) {
            throw new NegocioException("A temperatura máxima não pode ser menor que a mínima.");
        }

        DadosMeteorologicos salvo = repository.save(entidade);

        return mapper.paraResponseDto(salvo);
    }

    @Override
    public Page<DadosMeteorologicosResponseDto> listar(String cidade, int pagina, int tamanho) {

        LocalDate hoje = LocalDate.now();

        PageRequest pageRequest = PageRequest.of(
                pagina,
                tamanho,
                Sort.by(Sort.Direction.DESC, "dataPrevisao")
        );

        Page<DadosMeteorologicos> paginaDados;

        if (cidade == null || cidade.isBlank()) {
            paginaDados = repository.findByDataPrevisaoGreaterThanEqual(hoje, pageRequest);
        } else {
            paginaDados = repository.findByCidadeIgnoreCaseAndDataPrevisaoGreaterThanEqual(
                    cidade,
                    hoje,
                    pageRequest
            );
        }

        if (paginaDados.isEmpty()) {
            throw new RecursoNaoEncontradoException("Não há dados cadastrados.");
        }

        return paginaDados.map(mapper::paraResponseDto);
    }

    @Override
    public DadosMeteorologicosResponseDto buscarPorId(Long id) {

        DadosMeteorologicos entidade = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Dados meteorológicos não encontrados para o id informado.")
                );

        return mapper.paraResponseDto(entidade);
    }

    @Override
    public DadosMeteorologicosResponseDto buscarPrevisaoHoje(String cidade) {

        DadosMeteorologicos entidade = repository
                .findByCidadeIgnoreCaseAndDataPrevisao(cidade, LocalDate.now())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Não foi possível localizar a previsão do dia atual.")
                );

        return mapper.paraResponseDto(entidade);
    }

    @Override
    public List<DadosMeteorologicosResponseDto> buscarPrevisaoProximos7Dias(String cidade) {

        LocalDate hoje = LocalDate.now();
        LocalDate seteDias = hoje.plusDays(6);

        List<DadosMeteorologicos> dados = repository
                .findByCidadeIgnoreCaseAndDataPrevisaoBetweenOrderByDataPrevisaoAsc(
                        cidade,
                        hoje,
                        seteDias
                );

        if (dados.isEmpty()) {
            throw new RecursoNaoEncontradoException("Não há previsão para os próximos 7 dias.");
        }

        return dados.stream()
                .map(mapper::paraResponseDto)
                .toList();
    }

    @Override
    public DadosMeteorologicosResponseDto atualizar(Long id, DadosMeteorologicosRequestDto dto) {

        DadosMeteorologicos entidade = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Dados meteorológicos não encontrados para o id informado.")
                );

        entidade.setCidade(dto.getCidade());
        entidade.setDataPrevisao(dto.getDataPrevisao());
        entidade.setTempoDia(dto.getTempoDia());
        entidade.setTempoNoite(dto.getTempoNoite());
        entidade.setTemperaturaMaxima(dto.getTemperaturaMaxima());
        entidade.setTemperaturaMinima(dto.getTemperaturaMinima());
        entidade.setPrecipitacao(dto.getPrecipitacao());
        entidade.setHumidade(dto.getHumidade());
        entidade.setVelocidadeVento(dto.getVelocidadeVento());

        DadosMeteorologicos atualizado = repository.save(entidade);

        return mapper.paraResponseDto(atualizado);
    }

    @Override
    public void excluir(Long id) {

        DadosMeteorologicos entidade = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Dados meteorológicos não encontrados para o id informado.")
                );

        repository.delete(entidade);
    }
}