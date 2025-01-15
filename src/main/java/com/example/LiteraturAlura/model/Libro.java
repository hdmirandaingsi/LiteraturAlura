package com.example.LiteraturAlura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Long id;
    private String titulo;
    @ElementCollection
    private List<String> temas;
     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();
    @ElementCollection
    private List<String> estanterias;
    @ElementCollection
    private List<String> idiomas;
    private Boolean copyright;
    @Column(name="tipo_media")
    private String tipoMedia;
    @ElementCollection
    private Map<String, String> formatos;
    private Integer downloadCount;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.id = datosLibro.id();
        this.titulo = datosLibro.title();
        this.temas = datosLibro.subjects();
        this.autores = datosLibro.authors() != null ?
                 datosLibro.authors().stream()
                         .map(author -> new Autor(author.birthYear(), author.deathYear(), author.name()))
                        .distinct()
                         .toList(): Collections.emptyList();
        this.estanterias = datosLibro.bookshelves();
        this.idiomas = datosLibro.languages();
        this.copyright = datosLibro.copyright();
        this.tipoMedia = datosLibro.mediaType();
        this.formatos = datosLibro.formats();
        this.downloadCount = datosLibro.downloadCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getBookshelves() {
        return estanterias;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.estanterias = bookshelves;
    }

    public List<String> getLanguages() {
        return idiomas;
    }

    public void setLanguages(List<String> languages) {
        this.idiomas = idiomas;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return tipoMedia;
    }

    public void setMediaType(String mediaType) {
        this.tipoMedia = mediaType;
    }

    public Map<String, String> getFormatos() {
        return formatos;
    }

    public void setFormatos(Map<String, String> formatos) {
        this.formatos = formatos;
    }

   public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id) && Objects.equals(titulo, libro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo);
    }
}