CREATE TABLE dados_meteorologicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cidade VARCHAR(150) NOT NULL,
    data_previsao DATE NOT NULL,
    tempo_dia VARCHAR(50) NOT NULL,
    tempo_noite VARCHAR(50) NOT NULL,
    temperatura_maxima DECIMAL(5,2) NOT NULL,
    temperatura_minima DECIMAL(5,2) NOT NULL,
    precipitacao DECIMAL(5,2) NOT NULL,
    humidade DECIMAL(5,2) NOT NULL,
    velocidade_vento DECIMAL(5,2) NOT NULL,
    CONSTRAINT uk_dados_meteorologicos_cidade_data
    UNIQUE (cidade, data_previsao)
);