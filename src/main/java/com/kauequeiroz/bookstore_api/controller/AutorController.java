package com.kauequeiroz.bookstore_api.controller;


import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public Autor cadastrar(@RequestParam String nome,
                           @RequestParam String nacionalidade){
        return autorService.cadastrar(nome, nacionalidade);
    }

    @GetMapping
    public List<Autor> listarTodos() {
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Autor buscarPorId(@PathVariable Long id){
        return autorService.buscarPorId(id);
    }


}
