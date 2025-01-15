package com.example.LiteraturAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        Long id,
        String title,
        List<String> subjects,
        List<DatosAutor> authors,
        List<DatosAutor> translators,
        List<String> bookshelves,
        List<String> languages,
        Boolean copyright,
        @JsonAlias("media_type")
        String mediaType,
        Map<String, String> formats,
        @JsonAlias("download_count")
        Integer downloadCount
) {
}