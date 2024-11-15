package com.kcire.literalura.service;

import com.kcire.literalura.model.Autor;
import com.kcire.literalura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private IAutorRepository repository;

    public Autor obtenerAutorPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public void guardarAutor(Autor autor) {
        repository.save(autor);
    }

}
