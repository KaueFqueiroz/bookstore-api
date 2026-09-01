package com.kauequeiroz.bookstore_api.model;

import java.util.Arrays;
import java.util.List;


public class FilaEspera {

   private String[] fila;
   private int tamanho;
   private int capacidade;


   public FilaEspera(){
       this.capacidade = 100;
       this.fila = new String[capacidade];
       this.tamanho = 0;
   }

   public void adicionarFila(String nomeCliente){
       if (tamanho < capacidade){
           fila[tamanho++] = nomeCliente;
       }
   }

   public String removerPrimeiro(){
       if (tamanho > 0){
           String cliente = fila[0];
           for (int i = 0; i < tamanho - 1; i++){
               fila[i] = fila[i + 1];
           }
           tamanho--;
           return cliente;
       }
       return null;
   }

   public boolean filaVazia(){
       return tamanho == 0;
   }

   public List<String> verFila(){
       if (tamanho == 0){
           return List.of("Nenhum cliente na fila.");
       }
       return Arrays.asList(Arrays.copyOfRange(fila, 0, tamanho));
   }
}