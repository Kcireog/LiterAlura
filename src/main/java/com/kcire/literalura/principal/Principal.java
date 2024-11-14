package com.kcire.literalura.principal;

import com.kcire.literalura.model.Datos;
import com.kcire.literalura.service.ConsumoAPI;
import com.kcire.literalura.service.ConvierteDatos;
import com.kcire.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class Principal {


    private Scanner leer = new Scanner(System.in);
    private LibroService service = new LibroService();

    public void muestraMenu() {
        service.menu();
    }
}
