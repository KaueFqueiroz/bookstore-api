package com.kauequeiroz.bookstore_api.model.dto;

import jakarta.validation.constraints.*;

public class LivroRequest {


    @NotBlank (message = "Título é obrigatório")
    private String titulo;

    @NotNull(message = "Autor é obrigatório")
    private Long AutorId;

    @Min(value = 1000, message = "Ano de publicação inválido")
    @Max(value = 2026, message = "Ano de publicação não pode ser no futuro")
    private int anoPublicacao;

    @Positive(message = "Exemplares não pode ser negativo")
    private int exemplares;

    public String getTitulo() {
        return titulo;
    }

    public Long getAutorId() {
        return AutorId;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getExemplares() {
        return exemplares;
    }
}
