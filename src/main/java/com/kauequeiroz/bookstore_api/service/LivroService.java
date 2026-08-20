package com.kauequeiroz.bookstore_api.service;


import com.kauequeiroz.bookstore_api.model.Livro;
import com.kauequeiroz.bookstore_api.model.exception.LivroIndisponivelException;
import com.kauequeiroz.bookstore_api.model.exception.LivroNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private List<Livro> livros = new ArrayList<>();
    private Long proximoId = 1L;


    public Livro cadastrarLivro(String titulo, String autor, int anoPublicacao, int exemplares) {
        // verifica se já existe — mesma lógica do seu for original
        Optional<Livro> existente = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo)
                        && l.getAnoPublicacao() == anoPublicacao)
                .findFirst();

        if (existente.isPresent()) {
            Livro livro = existente.get();
            livro.setExemplares(livro.getExemplares() + exemplares);
            return livro;
        }

        Livro novo = new Livro(proximoId++, titulo, autor, anoPublicacao, exemplares);
        livros.add(novo);
        return novo;
    }

    public String emprestimo(String titulo){
        Livro livro = buscarPorTitulo(titulo);
        if (livro.getExemplares() <= 0) {
            throw new LivroIndisponivelException("Livro '" + titulo + "' sem exemplares disponíveis.");
        }
        livro.setExemplares(livro.getExemplares() - 1);
        return "Empréstimo de '" + titulo + "' realizado com sucesso!";
    }

    public String devolucao(String titulo){
        Livro livro = buscarPorTitulo(titulo);
        livro.setExemplares(livro.getExemplares() + 1);
        return "Livro '" + titulo + "' devolvido com sucesso";
    }

    public List<Livro> listarTodos(){
        return livros;
    }

    public int consultarDisponibilidade(String titulo){
        return buscarPorTitulo(titulo).getExemplares();
    }

    public Livro buscarPorId(Long id){
        return livros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    private Livro buscarPorTitulo(String titulo){
        return livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro '"  +titulo+ "' não encontrado."));
    }
}
