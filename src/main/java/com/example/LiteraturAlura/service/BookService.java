package com.example.LiteraturAlura.service;

import com.example.LiteraturAlura.dto.BookDTO;
import com.example.LiteraturAlura.dto.AuthorDTO;
import com.example.LiteraturAlura.model.Libro;
import com.example.LiteraturAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List; 
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> obtenerTodosLosLibros() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookDTO> buscarLibrosPorTitulo(String titulo) {
        return bookRepository.findByTituloContainsIgnoreCase(titulo).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
        public BookDTO obtenerLibroPorId(Long id){
        Optional<Libro> libro = bookRepository.findById(id);
        return libro.map(this::convertToDto).orElse(null);
    }
    private BookDTO convertToDto(Libro libro) {
         List<AuthorDTO> authorDtos = libro.getAutores().stream()
                 .map(autor -> new AuthorDTO(autor.getAnioNacimiento(), autor.getAnioMuerte(), autor.getNombre()))
                .collect(Collectors.toList());
           String formatosString = "";
         if(libro.getFormatos() != null){
            formatosString =  libro.getFormatos().entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
         }


        return new BookDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getTemas(),
                authorDtos,
                libro.getBookshelves(),
                libro.getLanguages(),
                libro.getCopyright(),
                libro.getMediaType(),
                formatosString,
                libro.getDownloadCount()
        );
    }
}