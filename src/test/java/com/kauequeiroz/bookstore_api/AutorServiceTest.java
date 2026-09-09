package com.kauequeiroz.bookstore_api;


import com.kauequeiroz.bookstore_api.model.Autor;
import com.kauequeiroz.bookstore_api.repository.AutorRepository;
import com.kauequeiroz.bookstore_api.service.AutorService;
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
public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    private Autor autor;


    @BeforeEach
    void setUp(){
        autor = new Autor("Robert Martin", "Americano");
    }

    @Test
    void deveCadastrarAutor(){
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor resultado = autorService.cadastrar("Robert Martin", "Americano");

        assertEquals("Robert Martin", resultado.getNome());
        assertEquals("Americano", resultado.getNacionalidade());
        verify(autorRepository).save(any(Autor.class));

    }

    @Test
    void deveListarTodosOsAutores(){
        when(autorRepository.findAll()).thenReturn(List.of(autor));

        List<Autor> resultado = autorService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Robert Martin", resultado.get(0).getNome());
        verify(autorRepository).findAll();
    }

    @Test
    void deveBuscarAutorPorID(){
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        Autor resultado = autorService.buscarPorId(1L);

        assertEquals("Robert Martin", resultado.getNome());
        assertEquals("Americano", resultado.getNacionalidade());
        verify(autorRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAutorNaoEncontrado(){
        when(autorRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> autorService.buscarPorId(99L));

        assertTrue(excecao.getMessage().contains("Autor não encontrado"));
        verify(autorRepository).findById(99L);
    }


}
