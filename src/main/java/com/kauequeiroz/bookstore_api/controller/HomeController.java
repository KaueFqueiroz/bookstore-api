package com.kauequeiroz.bookstore_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // marca como controller REST
public class HomeController {

    @GetMapping("/") // responde requisições GET na raiz da API
    public String home() {
        return "Bookstore API está no ar! " +
                "Acesse /auth/registrar para criar uma conta e começar a usar.";
    }
}