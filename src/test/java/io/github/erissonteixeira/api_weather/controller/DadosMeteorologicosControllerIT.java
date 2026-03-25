package io.github.erissonteixeira.api_weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.erissonteixeira.api_weather.dto.DadosMeteorologicosRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DadosMeteorologicosControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarBuscarEExcluirRegistro() throws Exception {

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
        
        String response = mockMvc.perform(post("/dados-meteorologicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(get("/dados-meteorologicos")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/dados-meteorologicos/1"))
                .andExpect(status().isNoContent());
    }
}