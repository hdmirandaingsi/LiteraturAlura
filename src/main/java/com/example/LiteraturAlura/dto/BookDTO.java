package com.example.LiteraturAlura.dto;

import java.util.List;

public record BookDTO(
        Long id,
        String titulo,
        List<String> temas,
        List<AuthorDTO> autores,
        List<String> estanterias,
        List<String> idiomas,
        Boolean copyright,
        String tipoMedia,
        String formatos,
        Integer conteoDescargas
) {
}