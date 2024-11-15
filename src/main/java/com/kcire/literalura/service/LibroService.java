package com.kcire.literalura.service;

import com.kcire.literalura.model.Libro;
import com.kcire.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private ILibroRepository repository;
    private Optional<Libro> libroBuscado;


    public Libro obtenerLibroPorNombre(String titulo) {
        return repository.findByTitulo(titulo);
    }

    public void guardarLibro(Libro libro) {
        repository.save(libro);
    }


}
