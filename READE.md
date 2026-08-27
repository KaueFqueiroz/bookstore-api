# Bookstore API 📚

API REST de gerenciamento de biblioteca construída com Spring Boot, baseada no Sistema Bibliotecário desenvolvido na disciplina de Estrutura de Dados.

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /livros | Lista todos os livros |
| GET | /livros/{id} | Busca livro por ID |
| POST | /livros | Cadastra novo livro |
| PUT | /livros/emprestimo | Realiza empréstimo |
| PUT | /livros/devolucao | Realiza devolução |
| GET | /livros/disponibilidade | Consulta exemplares disponíveis |
| GET | /h2-console | Console do banco de dados (dev) |
| POST | /autores | Cadastra novo autor |
| GET  | /autores | Lista todos os autores |
| GET  | /autores/{id} | Busca autor por ID |
| PUT | /livros/emprestimo | Realiza empréstimo (adiciona à fila se indisponível) |
| GET | /livros/fila | Retorna fila de espera atual |
| GET | /livros/historico | Retorna histórico de todas as operações |

## Tecnologias

- Java 26
- Spring Boot 4.0.7
- H2 Database

## Como rodar

```bash
git clone https://github.com/KaueFqueiroz/bookstore-api.git
cd bookstore-api
./mvnw spring-boot:run
```

Acesse: `http://localhost:8080/livros`