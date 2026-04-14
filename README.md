# Tarefas API
API REST para gerenciamento de tarefas com autenticação JWT, desenvolvida com Spring Boot e PostgreSQL.

## Tecnologias
- Java 21
- Spring Boot 4.0.5
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- PostgreSQL
- Docker
- JUnit 5 + Mockito

## Funcionalidades
- Cadastro de usuários
- Autenticação com geração de token JWT
- Criação, listagem, atualização e remoção de tarefas
- Cada usuário acessa apenas suas próprias tarefas
- Senhas armazenadas com hash (BCrypt)
- Proteção de endpoints com Spring Security

## Fluxo de Autenticação
1. Usuário se registra com email e senha
2. A senha é criptografada com BCrypt antes de ser armazenada
3. Usuário realiza login
4. As credenciais são validadas pelo Spring Security
5. Um token JWT é gerado e retornado
6. O cliente envia o token nas requisições protegidas
7. A API valida o token e identifica o usuário autenticado

## Decisões de Arquitetura
* Autenticação stateless utilizando JWT
* Separação em camadas (Controller, Service, Repository)
* Uso de DTOs para transferência de dados
* Relacionamento entre usuário e tarefas para isolamento de dados
* Spring Security para controle de acesso

## Segurança
* Autenticação baseada em JWT
* Senhas criptografadas com BCrypt
* Endpoints protegidos com Spring Security
* Acesso restrito às tarefas do próprio usuário

## Pré-Requisitos
- Java 21
- Docker

## Como rodar

Clone o repositório:
```bash
git clone git@github.com:JJ0o0/tarefas-api.git
cd tarefas-api
```

Suba o banco de dados:
```bash
docker run --name postgres-dev \
  -e POSTGRES_PASSWORD=suasenha \
  -e POSTGRES_USER=seuusuario \
  -e POSTGRES_DB=tarefasdb \
  -p 5432:5432 \
  -d postgres:16
```

Rode o projeto:
```bash
./mvnw spring-boot:run
```

## Endpoints

### Autenticação:

| Método  |    Endpoint     |           Descrição            |
|:-------:|:---------------:|:------------------------------:|
|  POST   | /auth/register  |     Registra novo usuário      |
|  POST   |   /auth/login   | Autentica e retorna token JWT  |

### Tarefas (requer autenticação):
| Método |   Endpoint    |        Descrição         |
|:------:|:-------------:|:------------------------:|
|  GET   |   /tarefas    | Lista tarefas do usuário |
|  POST  |   /tarefas    |     Cria nova tarefa     |
|  PUT   | /tarefas/{id} |     Atualiza tarefa      |
| DELETE | /tarefas/{id} |      Remove tarefa       |

## Exemplo de uso

### Registrar usuário:
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"nome": "Seu Nome", "email": "seuemail@provedor.com", "senha": "suasenha"}'
```

### Login:
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "seuemail@provedor.com", "senha": "suasenha"}'
```

Resposta:
```bash
{
  "token": "seu_token_jwt"
}
```

### Criar tarefa:
```bash
curl -X POST http://localhost:8080/tarefas \ 
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer seu_token_jwt" \
  -d '{"titulo": "Estudar Java", "descricao": "Estudar segurança com o JWT."}'
```