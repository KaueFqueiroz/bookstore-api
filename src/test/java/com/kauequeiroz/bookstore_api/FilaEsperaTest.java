package com.kauequeiroz.bookstore_api;

import com.kauequeiroz.bookstore_api.model.FilaEspera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilaEsperaTest {

    private FilaEspera fila;

    @BeforeEach
    void setUp(){
        fila = new FilaEspera();
    }

    @Test
    void deveIniciarVazia(){
        assertTrue(fila.filaVazia());
    }

    @Test
    void deveAdicionarClienteNaFila(){
        fila.adicionarFila("João");
        assertFalse(fila.filaVazia());
    }

    @Test
    void deveRemoverPrimerioClienteAdicionado(){
        fila.adicionarFila("João");
        fila.adicionarFila("Kauê");


       String primeiro = fila.removerPrimeiro();

        assertEquals("João", primeiro);
    }

    @Test
    void deveRetornarNullQuandoFilaVazia(){
        String resultado = fila.removerPrimeiro();
        assertNull(resultado);
    }

    @Test
    void deveListarClienteNaFila(){
       fila.adicionarFila("João");
       fila.adicionarFila("Kaue");

        assertEquals(2, fila.verFila().size());
    }

    @Test
    void deveRetornarMensagemQuandoFilaCheia(){
        for (int i = 0; i< 100; i++){
            fila.adicionarFila("Cliente " + i);
        }

        String mensagem = fila.adicionarFila("Cliente Extra");

        assertEquals("Fila de espera está cheia!", mensagem);
    }


}
