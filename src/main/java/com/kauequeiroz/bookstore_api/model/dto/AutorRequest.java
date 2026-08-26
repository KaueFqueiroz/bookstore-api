package com.kauequeiroz.bookstore_api.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AutorRequest {

    @NotBlank(message = "Nome inválido ")
    private String nome;

    @NotBlank(message = "Nacionalidade inválida")
    private String nacionalidade;

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
}
