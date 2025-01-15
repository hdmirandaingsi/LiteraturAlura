package com.example.LiteraturAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("birth_year")
        Integer birthYear,
        @JsonAlias("death_year")
        Integer deathYear,
        String name
) {
}