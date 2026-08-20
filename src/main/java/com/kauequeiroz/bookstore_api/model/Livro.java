package com.kauequeiroz.bookstore_api.model;

public class Livro {

    private Long id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int exemplares;

    public Livro(Long id, String titulo, String autor, int anoPublicacao, int exemplares){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = exemplares;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getExemplares() {
        return exemplares;
    }

    public void setExemplares(int exemplares){
        this.exemplares = exemplares;
    }

    public void adicionarExemplares(int exemplares){
        if (exemplares >= 0){
            this.exemplares = exemplares;
        }
        else{
            System.out.println("Quantidade Inválida");
        }
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + " | Autor: " + autor + " | Ano: " + anoPublicacao +
                " | Exemplares: " + exemplares;
    }
}
