package com.example.LiteraturAlura.model;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anioNacimiento;
    private Integer anioMuerte;
    private String nombre;

    public Autor(){}
      public Autor(Integer birthYear, Integer deathYear, String name){
       this.anioNacimiento = birthYear;
      this.anioMuerte = deathYear;
        this.nombre = name;
    }
    public Autor(DatosAutor datosAutor){
      this.anioNacimiento = datosAutor.birthYear();
      this.anioMuerte = datosAutor.deathYear();
      this.nombre = datosAutor.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(anioNacimiento, autor.anioNacimiento) && Objects.equals(anioMuerte, autor.anioMuerte) && Objects.equals(nombre, autor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anioNacimiento, anioMuerte, nombre);
    }
}