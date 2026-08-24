package com.kauequeiroz.bookstore_api.service;

import com.kauequeiroz.bookstore_api.model.Livro;
import com.kauequeiroz.bookstore_api.model.exception.LivroIndisponivelException;
import com.kauequeiroz.bookstore_api.model.exception.LivroNaoEncontradoException;
import com.kauequeiroz.bookstore_api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro cadastrarLivro(String titulo, String autor, int anoPublicacao, int exemplares) {
        // verifica se já existe — mesma lógica de antes
        return livroRepository.findByTituloIgnoreCase(titulo)
                .map(livroExistente -> {
                    livroExistente.setExemplares(livroExistente.getExemplares() + exemplares);
                    return livroRepository.save(livroExistente);
                })
                .orElseGet(() -> {
                    Livro novo = new Livro(titulo, autor, anoPublicacao, exemplares);
                    return livroRepository.save(novo);
                });
    }

    public String emprestimo(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro.getExemplares() <= 0) {
            throw new LivroIndisponivelException("Livro '" + titulo + "' sem exemplares disponíveis.");
        }
        livro.setExemplares(livro.getExemplares() - 1);
        livroRepository.save(livro);
        return "Empréstimo de '" + titulo + "' realizado com sucesso!";
    }

    public String devolucao(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        livro.setExemplares(livro.getExemplares() + 1);
        livroRepository.save(livro);
        return "Livro '" + titulo + "' devolvido com sucesso!";
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public int consultarDisponibilidade(String titulo) {
        return buscarPorTitulo(titulo).getExemplares();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));
    }

    private Livro buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloIgnoreCase(titulo)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro '" + titulo + "' não encontrado."));
    }
}