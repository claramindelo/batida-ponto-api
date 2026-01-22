# API de Batida de Ponto - Dixi Soluções

API REST desenvolvida com Spring Boot para sistema de controle de ponto eletrônico, com validações de segurança e armazenamento em memória.

## 🚀 Tecnologias

- Java 21
- Spring Boot 4.1.0
- Maven
- Lombok
- Armazenamento em memória (RAM)

## ✨ Funcionalidades

✅ Registro de ponto com **data/hora do servidor**
✅ Validação de localização obrigatória (GPS)
✅ Intervalo mínimo de 1 minuto entre marcações
✅ Suporte para foto em base64
✅ Marcações desconsideradas (histórico de tentativas inválidas)
✅ Filtros por período
✅ CORS habilitado para integração com frontend

## 📋 Endpoints

### Registrar Ponto
```http
POST /api/ponto/registrar
Content-Type: application/json

{
  "foto": "base64_string",
  "latitude": -25.4284,
  "longitude": -49.2733
}
```

### Registrar Marcação Desconsiderada
```http
POST /api/ponto/desconsiderado
Content-Type: application/json

{
  "motivo": "Marcação desconsiderada por proximidade",
  "foto": "base64_string",
  "latitude": -25.4284,
  "longitude": -49.2733
}
```

### Listar Registros
```http
GET /api/ponto/registros
```

### Listar Desconsiderados
```http
GET /api/ponto/desconsiderados
```

### Filtrar por Período
```http
GET /api/ponto/filtrar?dataInicial=21/01/2026&dataFinal=21/01/2026
GET /api/ponto/filtrar-desconsiderados?dataInicial=21/01/2026&dataFinal=21/01/2026
```

## 🛠️ Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.9+

### Instalação

1. Clone o repositório
```bash
git clone https://github.com/claramindelo/batida-ponto-api.git
cd batida-ponto-api
```

2. Execute o projeto
```bash
./mvnw spring-boot:run
```

3. A API estará disponível em:
```
http://localhost:8080
```

## 📁 Estrutura do Projeto
```
src/main/java/com/dixi/batidaponto/
├── controller/
│   └── PontoController.java
├── model/
│   ├── MarcacaoPonto.java
│   ├── RegistroPonto.java
│   └── RegistroDesconsiderado.java
├── service/
│   └── PontoService.java
├── repository/
│   └── PontoRepository.java
└── BatidaPontoApiApplication.java
```

## 🔒 Segurança

- ✅ **Hora do servidor**: Usa `LocalDateTime.now()` para prevenir fraudes
- ✅ **Localização obrigatória**: Bloqueia registros sem GPS
- ✅ **Intervalo mínimo**: Valida 60 segundos entre marcações

## 🌐 Integração com Frontend

Este backend está integrado com o frontend React:
- Repositório: https://github.com/claramindelo/batida-web-dixi

## 📝 Observações

⚠️ **Armazenamento em memória**: Os dados são perdidos ao reiniciar o servidor
- Para produção, considere adicionar um banco de dados de sua preferência

## 👩‍💻 Desenvolvido por

Clara Mindelo - Desafio Técnico