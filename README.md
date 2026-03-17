# API Weather

API REST para gerenciamento de dados meteorológicos, desenvolvida com **Java 21 + Spring Boot**.

A aplicação permite cadastro, consulta, atualização e remoção de previsões do tempo, além de fornecer endpoints específicos para **previsão do dia atual** e **previsão dos próximos 7 dias**.

A persistência é feita com **Spring Data JPA**, as migrations são controladas pelo **Flyway** e a documentação da API é gerada automaticamente via **Swagger (springdoc-openapi)**.

O projeto utiliza:

- **MySQL via Docker** para execução principal
- **H2 em memória** para testes automatizados

---

# Visão geral

Esta API fornece os seguintes recursos:

- Cadastro de previsões meteorológicas
- Listagem paginada de previsões
- Consulta por ID
- Atualização de registros
- Remoção de registros
- Consulta da previsão do dia atual por cidade
- Consulta da previsão dos próximos 7 dias por cidade

A aplicação roda em:

http://localhost:8080

---

# Stack

- Java 21
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- Flyway (migrations)
- MySQL (Docker)
- H2 Database (testes)
- MapStruct
- Lombok
- springdoc-openapi (Swagger)
- JUnit 5
- Mockito

---

# Arquitetura

A aplicação segue uma arquitetura em camadas:

Controller  
↓  
Service  
↓  
Repository  
↓  
Database  

Estrutura principal:

src/main/java/io/github/erissonteixeira/api_weather

- config
- controller
- dto
- entity
- exception
- mapper
- repository
- service
  - serviceimpl

Essa separação garante organização, testabilidade e manutenção do código.

---

# Regras de negócio

- Não permite duplicidade de **cidade + data**
- Temperatura máxima não pode ser menor que a mínima
- Listagem retorna apenas dados a partir da data atual
- Previsão de 7 dias considera o intervalo de hoje até +6 dias

---

# Como rodar

## Pré-requisitos

- Java 21  
- Maven  
- Docker  

---

## Subir banco MySQL com Docker

Na raiz do projeto:

docker-compose up -d

---

## Rodar aplicação

mvn spring-boot:run

ou pela IDE (IntelliJ)

---

# Banco de dados

O projeto utiliza:

- MySQL via Docker
- Flyway para controle de migrations

As tabelas são criadas automaticamente ao iniciar a aplicação.

---

# Migrations (Flyway)

O Flyway executa automaticamente as migrations ao iniciar a aplicação.

Durante o startup você verá logs como:

Successfully validated migrations  
Successfully applied migrations  

---

# Swagger / OpenAPI

Documentação disponível em:

http://localhost:8080/swagger-ui.html

---

# Endpoints principais

## Criar previsão

POST /dados-meteorologicos

---

## Listar previsões

GET /dados-meteorologicos?pagina=0&tamanho=10

---

## Buscar por ID

GET /dados-meteorologicos/{id}

---

## Atualizar

PUT /dados-meteorologicos/{id}

---

## Excluir

DELETE /dados-meteorologicos/{id}

---

## Previsão de hoje

GET /dados-meteorologicos/hoje/{cidade}

---

## Próximos 7 dias

GET /dados-meteorologicos/proximos-7-dias/{cidade}

---

# Testes

A aplicação possui dois tipos de testes:

## Testes unitários

- Service (`DadosMeteorologicosServiceImpl`)
- Validação das regras de negócio

## Testes de integração

- Controller (`MockMvc`)
- Fluxo completo da aplicação (API → Service → Repository → Banco H2)

---

# CORS

Configurado para permitir requisições do frontend:

http://localhost:5173

---

# Configurações principais

Porta da aplicação

8080

Configurações relevantes:

spring.jpa.open-in-view=false  
spring.jpa.hibernate.ddl-auto=validate  
flyway.enabled=true  

---

# Checklist de validação

- Backend sobe corretamente
- Swagger acessível
- CRUD funcionando
- Regra de 7 dias funcionando
- Flyway executando migrations
- Docker MySQL funcionando
- Testes unitários passando
- Testes de integração passando
- CORS liberado para frontend

---

# Autor

Desenvolvido por **Erisson Teixeira**
