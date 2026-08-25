package com.kauequeiroz.bookstore_api.repository;

import com.kauequeiroz.bookstore_api.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
