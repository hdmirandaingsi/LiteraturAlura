package com.example.LiteraturAlura.repository;
  
import com.example.LiteraturAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Autor, Long> {
}