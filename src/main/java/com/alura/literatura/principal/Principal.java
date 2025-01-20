package com.alura.literatura.principal;

import com.alura.literatura.model.ApiResponse;
import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    =====================================
                             LITERATURA CHALLENGE
                    =====================================
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    =====================================
                    0 - Salir
                    =====================================
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
    //Buscar en la api
    private void buscarLibroPorTitulo() {
        System.out.println("Ingresar el nombre del libro que desea buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        ApiResponse datos = conversor.obtenerDatos(json, ApiResponse.class);
        if (datos.results().isEmpty())
        {
            System.out.println("Libro no encontrado....");
            return;
        }
        System.out.println(datos.results().get(0));
    }
}

