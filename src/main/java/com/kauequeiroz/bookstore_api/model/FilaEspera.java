package com.kauequeiroz.bookstore_api.model;

import java.util.ArrayList;
import java.util.List;


public class FilaEspera {

    private List<String> fila = new ArrayList<>();


    public void adicionarAFila(String nomeCliente) {
        fila.add(nomeCliente);
    }

    public String removerPrimeiro() {
        if (!fila.isEmpty()) {
            return fila.remove(0);
        }
        return null;
    }

    public boolean filaVazia() {
        return fila.isEmpty();
    }

    public List<String> verFila() {
        return fila;
    }
}