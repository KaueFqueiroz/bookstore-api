package com.kauequeiroz.bookstore_api.model.exception;

public class LivroIndisponivelException extends RuntimeException {
    public LivroIndisponivelException(String message) {
        super(message);
    }
}
