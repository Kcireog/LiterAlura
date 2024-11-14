package com.kcire.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int fechaNacimiento;
    private int fechaFallecimiento;

    @ManyToMany(mappedBy = "autores") // Indica que esta es la relación inversa
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = Optional.of(datosAutor.fechaNacimiento()).orElse(0);
        this.fechaFallecimiento = datosAutor.fechaFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public int getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(int fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    @Override
    public String toString() {
        //si fechaNacimiento es = 0, mostrar "N/A", sino el valor correspondiente
        String fechaFallecimientoStr = (fechaFallecimiento == 0) ? "N/A" : String.valueOf(fechaFallecimiento);
        return "--------Autor--------" +
                "nombre='" + nombre + '\'' +
                " Año Nacimiento=" + fechaNacimiento +
                " Año Fallecimiento=" + fechaFallecimientoStr +
                ", libros=" + libros +
                "---------------------";
    }
}
