package com.kauequeiroz.bookstore_api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HistoricoOperacoes {

   private String[] historico;
   private int topo;
   private int capacidade;

   public HistoricoOperacoes(){
       this.capacidade = 100;
       this.historico = new String[capacidade];
       this.topo = -1;

   }


       public void addOperacao(String operacao){
           if (topo < capacidade - 1){
               historico[++topo] = operacao;
           }
           else {
               System.out.println("Histórico cheio!");
           }
       }

       public String desafazerUltimaOperacao(){
            if (topo >= 0){
           return historico[topo--];
            }
            return null;
       }

       public List<String> verHistorico(){
            if(topo == 1){
                return List.of("Histórico vazio");
            }
            return Arrays.asList(Arrays.copyOfRange(historico, 0, topo + 1));
       }

}