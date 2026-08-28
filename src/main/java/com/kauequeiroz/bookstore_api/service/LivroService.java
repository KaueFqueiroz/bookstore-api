package com.kauequeiroz.bookstore_api.service;

import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.model.FilaEspera;
import com.kauequeiroz.bookstore_api.model.HistoricoOperacoes;
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

    private static FilaEspera filaEspera = new FilaEspera(); // fila de espera para livros indisponíveis
    private static HistoricoOperacoes historico = new HistoricoOperacoes();

    public Livro cadastrarLivro(String titulo, Autor autor, int anoPublicacao, int exemplares) {
        return livroRepository.findByTituloIgnoreCase(titulo)
                .map(livroExistente -> {
                    livroExistente.setExemplares(livroExistente.getExemplares() + exemplares);
                    historico.addOperacao("Atualização: " + titulo + " — exemplares adicionados: " + exemplares); // registra na pilha
                    return livroRepository.save(livroExistente);
                })
                .orElseGet(() -> {
                    Livro novo = new Livro(titulo, autor, anoPublicacao, exemplares);
                    historico.addOperacao("Cadastro: " + titulo + " por " + autor.getNome()); // registra na pilha
                    return livroRepository.save(novo);
                });
    }

        public String emprestimo(String titulo, String nomeCliente) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro.getExemplares() <= 0) {
            filaEspera.adicionarFila(nomeCliente); // adiciona o cliente na fila de espera
            historico.addOperacao("Fila de espera: " + nomeCliente + " aguardando " + titulo); // registra na pilha
            return "Livro indisponível. " + nomeCliente + " adicionado à fila de espera.";
        }

        livro.setExemplares(livro.getExemplares() - 1);
        historico.addOperacao("Empréstimo: " + titulo + " para " + nomeCliente);
        livroRepository.save(livro);
        return "Empréstimo de '" + titulo + "' realizado com sucesso!";
    }

    public String devolucao(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        livro.setExemplares(livro.getExemplares() + 1);
        historico.addOperacao("Devolução: " + titulo); // registra na pilha

        if (!filaEspera.filaVazia()) { // verifica se tem alguém na fila
            String proximoCliente = filaEspera.removerPrimeiro(); // remove o primeiro da fila
            livro.setExemplares(livro.getExemplares() - 1);
            historico.addOperacao("Empréstimo automático: " + titulo + " para " + proximoCliente);
            livroRepository.save(livro);
            return "Livro devolvido e emprestado automaticamente para: " + proximoCliente;
        }

        livroRepository.save(livro);
        return "Livro '" + titulo + "' devolvido com sucesso!";
    }

    public List<String> verHistorico() {
        return historico.verHistorico();
    }

    public List<String> verFilaEspera() {
        return filaEspera.verFila();
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