package com.kcire.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name= "libros")
public class Libro {

    private Long id;

    private String titulo;

    private List<Autor> autores;

    private List<String> idiomas;

    private Double numDescargas;


}
