package com.kcire.literalura.principal;

import com.kcire.literalura.service.ConsumoAPI;
import com.kcire.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner leer = new Scanner(System.in);


}
