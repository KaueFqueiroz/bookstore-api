package com.kauequeiroz.bookstore_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "📚 Bookstore API\n\n" +
                "API REST de gerenciamento de biblioteca com Spring Boot, JWT e PostgreSQL.\n\n" +
                "Para testar a API:\n" +
                "1. POST /auth/registrar — crie uma conta\n" +
                "2. POST /auth/login — faça login e receba o token JWT\n" +
                "3. Use o token no header: Authorization: Bearer {token}\n\n" +
                "Documentação completa: https://github.com/KaueFqueiroz/bookstore-api";
    }
}