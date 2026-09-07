package com.kauequeiroz.bookstore_api;

import com.kauequeiroz.bookstore_api.model.HistoricoOperacoes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoOperacoesTest {

    private HistoricoOperacoes historico;

    @BeforeEach
    void setUp(){
        historico = new HistoricoOperacoes();
    }

    @Test
    void deveIniciarVazio(){
        assertEquals("Histórico vazio",historico.verHistorico().get(0));
    }

    @Test
    void deveAdicionarOperacao(){
        historico.addOperacao("Cadastro: Revolução dos Bichos");
        assertEquals(1, historico.verHistorico().size());
    }

    @Test
    void deveDesfazerUltimaOperacao(){
        historico.addOperacao("Cadastro: Revolução dos Bichos");
        historico.addOperacao("Empréstimo: Revolução dos Bichos");

        String ultimo = historico.desfazerUltimaOperacao();

        assertEquals("Empréstimo: Revolução dos Bichos", ultimo);
    }

    @Test
    void deveRetornarNullQuandoVazio(){
        String resultado = historico.desfazerUltimaOperacao();

        assertNull(resultado);
    }

    @Test
    void deveRegistrarMultiplasOperacoes(){
        historico.addOperacao("Cadastro: Hábitos Átomico");
        historico.addOperacao("Cadastro: A Odisséia");
        historico.addOperacao("Reserva: Hábitos Átomico");

        assertEquals(3, historico.verHistorico().size());
    }


    @Test
    void deveDimuirTamanho() {
        historico.addOperacao("Cadastro: Hábitos Átomico");
        historico.addOperacao("Cadastro: A Odisséia");
        historico.addOperacao("Reserva: Hábitos Átomico");

        historico.desfazerUltimaOperacao();

        assertEquals(2, historico.verHistorico().size());
    }

}
