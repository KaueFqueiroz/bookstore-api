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

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;


    @GetMapping
    public List<Livro> listarTodos(){
        return livroService.listarTodos();
    }

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
    public String emprestimo(@RequestParam String titulo) {
        return livroService.emprestimo(titulo);
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

}
