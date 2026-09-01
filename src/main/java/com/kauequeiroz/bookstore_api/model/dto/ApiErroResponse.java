package com.kauequeiroz.bookstore_api.model.dto;

import java.time.LocalDateTime;

public class ApiErroResponse {
    private String mensagem;
    private String timestamp;

    public ApiErroResponse(String mensagem){
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
