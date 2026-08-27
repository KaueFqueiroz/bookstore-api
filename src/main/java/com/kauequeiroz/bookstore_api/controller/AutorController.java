package com.kauequeiroz.bookstore_api.controller;


import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.model.dto.AutorRequest;
import com.kauequeiroz.bookstore_api.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public Autor cadastrar(@Valid @RequestBody AutorRequest request){
        return autorService.cadastrar(request.getNome(), request.getNacionalidade());
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
