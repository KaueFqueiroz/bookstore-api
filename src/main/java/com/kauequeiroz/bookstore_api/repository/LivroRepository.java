package com.kauequeiroz.bookstore_api.repository;


import com.kauequeiroz.bookstore_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloIgnoreCase(String titulo);

    @Query("SELECT l FROM Livro l WHERE l.exemplares > 0")
    List<Livro> buscarDisponiveis();

    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> buscarPorTrechoTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<Livro> buscarPorNomeAutor(@Param("nomeAutor") String nomeAutor);

    @Query("SELECT COUNT(l) FROM Livro l WHERE l.exemplares > 0")
    Long contarDisponiveis();
}
