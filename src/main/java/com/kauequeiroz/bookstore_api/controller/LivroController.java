package com.kauequeiroz.bookstore_api.controller;


import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.model.Livro;
import com.kauequeiroz.bookstore_api.model.dto.LivroRequest;
import com.kauequeiroz.bookstore_api.service.AutorService;
import com.kauequeiroz.bookstore_api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    public Livro cadastrar(@Valid @RequestBody LivroRequest request){
        Autor autor = autorService.buscarPorId(request.getAutorId());
        return livroService.cadastrarLivro(request.getTitulo(), autor, request.getAnoPublicacao(), request.getExemplares());
    }

    @PutMapping("/emprestimo")
    public String emprestimo(@RequestParam String titulo,
                             @RequestParam String nomeCliente) {

        return livroService.emprestimo(titulo, nomeCliente);
    }

    @PutMapping("/devolucao")
    public String devolucao(@RequestParam String titulo){
        return livroService.devolucao(titulo);
    }

    @GetMapping("/disponibilidade")
    public String consultarDisponibilidade(@RequestParam String titulo) {
        int quantidade = livroService.consultarDisponibilidade(titulo);
        return "Exemplares disponíveis de '" + titulo + "': " + quantidade;

    }

    @GetMapping("/historico")
    public List<String> verHistorico() {
        return livroService.verHistorico();
    }


    @GetMapping("/fila")
    public List<String> verFilaEspera() {
        return livroService.verFilaEspera();
    }


    @GetMapping("/buscar")
    public List<Livro> buscarPorTrechoTitulo(@RequestParam String titulo){
        return livroService.buscarPorTrechoTitulo(titulo);
    }

    @GetMapping("/autor")
    public List<Livro> buscarPorNomeAutor(@RequestParam String nomeAutor){
        return livroService.buscarPorNomeAutor(nomeAutor);
    }

    @GetMapping("/count")
    public Long contarDisponiveis(){
        return livroService.contarDisponiveis();
    }

    @GetMapping
    public Page<Livro> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "titulo") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());
        return livroService.listarTodosPaginado(pageable);
    }

    // GET /livros/disponiveis?page=0&size=2
    @GetMapping("/disponiveis")
    public Page<Livro> buscarDisponiveis(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("titulo").ascending());
        return livroService.buscarDisponiveisPaginado(pageable);
    }

}
