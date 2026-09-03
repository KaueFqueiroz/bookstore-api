package com.kauequeiroz.bookstore_api.model.exception;

import com.kauequeiroz.bookstore_api.model.dto.ApiErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ApiErroResponse> handleLivroNaoEncontrado(LivroNaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErroResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErroResponse> handleValidacao(MethodArgumentNotValidException e) {
        String mensagem = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErroResponse(mensagem));
    }

    // trata qualquer outra exceção não mapeada → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErroResponse> handleGenerico(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErroResponse("Erro interno: " + e.getMessage()));
    }


}
