package com.kauequeiroz.bookstore_api;


import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.model.Livro;
import com.kauequeiroz.bookstore_api.model.exception.LivroNaoEncontradoException;
import com.kauequeiroz.bookstore_api.repository.LivroRepository;
import com.kauequeiroz.bookstore_api.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    private Autor autor;
    private Livro livro;

    @BeforeEach
    void setUP(){
        autor = new Autor("RObert Martin", "Americano");
        livro = new Livro("Clean Code", autor, 2008, 3);
    }

    @Test
    void deveListarLivrosDisponiveis(){
        when(livroRepository.buscarDisponiveis()).thenReturn(List.of(livro));

        List<Livro> resultado = livroService.buscarDisponiveis();

        assertEquals(1, resultado.size());
        assertEquals("Clean Code", resultado.get(0).getTitulo());
        verify(livroRepository).buscarDisponiveis();
    }

    @Test
    void deveBuscarLivroPorId(){
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        Livro resultado = livroService.buscarPorId(1L);

        assertEquals("Clean Code", resultado.getTitulo());
        verify(livroRepository).findById(1L);
    }

    @Test // testa buscar por id quando livro não existe
    void deveLancarExcecaoQuandoLivroNaoEncontrado(){
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(LivroNaoEncontradoException.class, () -> livroService.buscarPorId(99L));
    }

    @Test
    void deveRealizarEmprestimoQuandoTemExemplares(){
        when(livroRepository.findFirstByTituloIgnoreCase("Clean Code")).thenReturn(Optional.of(livro));
        when(livroRepository.save(livro)).thenReturn(livro);

        String resultado = livroService.emprestimo("Clean Code", "Kaue");

        assertTrue(resultado.contains("sucesso"));
        assertEquals(2, livro.getExemplares());
        verify(livroRepository).save(livro);
    }

    @Test
    void deveAdicionarFilaQuandoSemExemplares(){
        Livro livroSemExemplar = new Livro("Clean Code", autor, 2008, 0);
        when(livroRepository.findFirstByTituloIgnoreCase("Clean Code")).thenReturn(Optional.of(livroSemExemplar));

        String resultado = livroService.emprestimo("Clean Code", "Kaue");

        assertTrue(resultado.contains("fila"));
        verify(livroRepository, never()).save(any());
    }

}


