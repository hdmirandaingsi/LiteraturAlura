package com.example.LiteraturAlura.controller;

import com.example.LiteraturAlura.dto.BookDTO;
import com.example.LiteraturAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> obtenerTodosLosLibros() {
        return bookService.obtenerTodosLosLibros();
    }
    
    @GetMapping("/buscar")
    public List<BookDTO> buscarLibrosPorTitulo(@RequestParam String titulo) {
        return bookService.buscarLibrosPorTitulo(titulo);
    }
    
     @GetMapping("/{id}")
    public BookDTO obtenerLibroPorId(@PathVariable Long id) {
        return bookService.obtenerLibroPorId(id);
    }
}