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
        autor = new Autor("Robert Martin", "americano");
    }

    @Test
    void deveCadastrarAutor(){
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);


    }


}
