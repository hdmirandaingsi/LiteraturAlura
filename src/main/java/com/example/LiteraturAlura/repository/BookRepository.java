package com.example.LiteraturAlura.repository;

import com.example.LiteraturAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Libro, Long> {
      @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Libro> findByTituloContainsIgnoreCase(String titulo);

    @Query("SELECT l FROM Libro l WHERE LOWER(l.idiomas) LIKE LOWER(CONCAT('%', :idioma, '%'))")
    List<Libro> findByIdiomasContainsIgnoreCase(String idioma);
}