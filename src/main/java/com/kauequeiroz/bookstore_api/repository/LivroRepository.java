package com.kauequeiroz.bookstore_api.repository;


import com.kauequeiroz.bookstore_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findBytituloIgnoreCase(String titulo);
}
