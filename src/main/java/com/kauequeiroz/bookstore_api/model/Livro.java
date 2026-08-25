package com.kauequeiroz.bookstore_api.model;


import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String titulo;
    private int anoPublicacao;
    private int exemplares;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(String titulo, Autor autor, int anoPublicacao, int exemplares){
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = exemplares;
    }

    public Livro(){
    }

    public Long getId() {
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public Autor getAutor() {
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
