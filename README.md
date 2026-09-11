# Bookstore API 📚

API REST de gerenciamento de biblioteca construída com Java e Spring Boot, baseada no Sistema Bibliotecário 
desenvolvido na disciplina de Estrutura de Dados da faculdade.


## 🌐 API em produção
https://bookstore-api-production-5798.up.railway.app


## 🚀 Tecnologias

- Java 26
- Spring Boot 4.0.7
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- JUnit 5 + Mockito
- Docker
- Railway (deploy)

## 📋 Funcionalidades

- CRUD completo de livros e autores
- Autenticação e autorização com JWT
- Fila de espera para livros indisponíveis (implementada do zero como TAD)
- Histórico de operações em pilha (implementado do zero como TAD)
- Paginação e ordenação
- Queries customizadas com JPQL
- Tratamento global de erros
- Testes unitários com JUnit 5 e Mockito

## 🔐 Autenticação

Todas as rotas exceto `/auth/**` exigem token JWT.

**Registrar:**

POST /auth/registrar
Body: { "email": "seu@email.com", "senha": "sua_senha" }

**Login:**
POST /auth/login
Body: { "email": "seu@email.com", "senha": "sua_senha" }

Adiciona o token retornado no header:
Authorization: Bearer {token}

## 📡 Endpoints

### Autores
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | /autores | Cadastra novo autor |
| GET | /autores | Lista todos os autores |
| GET | /autores/{id} | Busca autor por ID |

### Livros
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /livros | Lista livros com paginação |
| GET | /livros/{id} | Busca livro por ID |
| POST | /livros | Cadastra novo livro |
| PUT | /livros/emprestimo | Realiza empréstimo |
| PUT | /livros/devolucao | Realiza devolução |
| GET | /livros/disponiveis | Lista livros disponíveis |
| GET | /livros/disponibilidade | Consulta exemplares |
| GET | /livros/buscar | Busca por trecho do título |
| GET | /livros/autor | Busca por nome do autor |
| GET | /livros/fila | Fila de espera atual |
| GET | /livros/historico | Histórico de operações |
| GET | /livros/count | Conta livros disponíveis |

## 🏗️ Como rodar localmente

### Pré-requisitos
- Java 21+
- PostgreSQL
- Maven

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/KaueFqueiroz/bookstore-api.git
cd bookstore-api
```

2. Configure as variáveis de ambiente:
DB_PASSWORD=sua_senha_postgresql

3. Rode o projeto:
```bash
./mvnw spring-boot:run
```

## 🧪 Testes

```bash
./mvnw test
```

## 👤 Autor

**Kauê Queiroz**
- GitHub: [@KaueFqueiroz](https://github.com/KaueFqueiroz)
- LinkedIn: [linkedin.com/in/seu-perfil](https://linkedin.com/in/seu-perfil)