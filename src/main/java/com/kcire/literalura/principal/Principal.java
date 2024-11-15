package com.kcire.literalura.principal;

import com.kcire.literalura.model.*;
import com.kcire.literalura.service.AutorService;
import com.kcire.literalura.service.ConsumoAPI;
import com.kcire.literalura.service.ConvierteDatos;
import com.kcire.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Scanner;

public class Principal {


    private Scanner leer = new Scanner(System.in);
    private LibroService libroService = new LibroService();
    private AutorService autorService = new AutorService();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void menu() {
        var opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                      ***MENU***
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    
                    0- Salir
                    ******************************
                    Seleccione una opción:
                    """);
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1:
                    obtenerLibroPorTitulo();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opcion no valida...");
            }

        }

    }

    public void obtenerLibroPorTitulo() {
        System.out.println("Titulo del libro a buscar: ");
        var nombreLibro = leer.nextLine();

        // Obtener datos de la API
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + normalizarTextoParaURL(nombreLibro));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        // Buscar libro en los resultados de la API
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> normalizarTexto(l.titulo()).contains(normalizarTexto(nombreLibro)))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado...");
            manejarLibro(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado...");
        }
    }

    // Normalizar texto para búsquedas
    private String normalizarTexto(String texto) {
        return texto.toUpperCase().trim();
    }

    // Convertir espacios en URL-friendly
    private String normalizarTextoParaURL(String texto) {
        return texto.replace(" ", "+");
    }

    // Manejar lógica de creación/actualización del libro
    private void manejarLibro(DatosLibro datosLibro) {
        Libro libroExistente = libroService.obtenerLibroPorNombre(datosLibro.titulo());
        if (libroExistente != null) {
            System.out.println("El libro existe en el sistema...");
            System.out.println(libroExistente);
            return;
        }

        // Crear libro si no existe
        Libro libro = new Libro(datosLibro);
        datosLibro.autor().forEach(datosAutor -> libro.agregarAutor(obtenerOGuardarAutor(datosAutor)));
        libroService.guardarLibro(libro);

        System.out.println("Libro guardado en la bd...");
        System.out.println(libro);
    }

    // Obtener o guardar autor
    private Autor obtenerOGuardarAutor(DatosAutor datosAutor) {
        Autor autor = autorService.obtenerAutorPorNombre(datosAutor.nombre());
        if (autor != null) {
            System.out.println("El autor existe en el sistema...");
            return autor;
        }

        // Crear nuevo autor
        autor = new Autor(datosAutor);
        autorService.guardarAutor(autor);
        return autor;
    }
}
