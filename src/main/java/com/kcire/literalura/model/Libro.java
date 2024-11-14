package com.kcire.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "libro_autor", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "libro_id"), // Clave foránea hacia `libros`
            inverseJoinColumns = @JoinColumn(name = "autor_id") // Clave foránea hacia `autores`
    )
    private List<Autor> autores;

    @Enumerated(EnumType.STRING)
    private Idioma idiomas;

    private Double numDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idiomas = Idioma.fromString(datosLibro.idiomas().get(0));
        this.numDescargas = datosLibro.numDescargas();
    }

    //validación autor, agregar solo el que no este agregado ya
    public void agregarAutor(Autor autor) {
        if(!autores.contains(autor)) {
            autores.add(autor);
        }
    }
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(Double numDescargas) {
        this.numDescargas = numDescargas;
    }

    @Override
    public String toString() {
        return "-----------Libro--------------" +
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numDescargas=" + numDescargas +
                "------------------------------";
    }
}
