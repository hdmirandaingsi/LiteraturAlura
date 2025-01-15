package com.example.LiteraturAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListaDatosLibro (
        Integer count,
        String next,
        String previous,
        List<DatosLibro> results
){}