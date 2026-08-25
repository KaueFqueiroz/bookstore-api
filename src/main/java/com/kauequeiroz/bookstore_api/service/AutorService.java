package com.kauequeiroz.bookstore_api.service;

import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {


    @Autowired
    private AutorRepository autorRepository;

    public Autor cadastrar(String nome, String nacionalidade){
        Autor autor = new Autor(nome, nacionalidade);
        return autorRepository.save(autor);
    }

    public List<Autor> listarTodos(){
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id){
        return autorRepository.findById(id)
                .orElseThrow(( ) -> new RuntimeException("Autor não encontado"));
    }
}
