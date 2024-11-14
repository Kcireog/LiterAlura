package com.kcire.literalura.service;

import com.kcire.literalura.model.Datos;
import com.kcire.literalura.model.DatosLibro;
import com.kcire.literalura.model.Libro;
import com.kcire.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {

    @Autowired
    private ILibroRepository repository;
    private Optional<Libro> libroBuscado;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner leer = new Scanner(System.in);

    private String json = consumoApi.obtenerDatos(URL_BASE);
    private Datos datos = conversor.obtenerDatos(json, Datos.class);

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

        json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l->l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();


        if (libroBuscado.isPresent()) {
            System.out.println("El libro buscado es: " + libroBuscado.get());
            repository.save(libroBuscado);
        } else {
            System.out.println("Libro no encontrado...");
        }

    }


}
