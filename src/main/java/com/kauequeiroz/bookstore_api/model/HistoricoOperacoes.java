package com.kauequeiroz.bookstore_api.model;

import java.util.ArrayList;
import java.util.List;


public class HistoricoOperacoes {

    private List<String> historico = new ArrayList<>();


    public void addOperacao(String operacao) {
        historico.add(operacao);
    }


    public String desfazerUltimaOperacao() {
        if (!historico.isEmpty()) {
            return historico.remove(historico.size() - 1);
        }
        return null;
    }

    public List<String> verHistorico() {
        return historico;
    }
}