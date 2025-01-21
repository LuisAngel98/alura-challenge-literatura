package com.alura.literatura.principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import com.alura.literatura.service.AutorService;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;

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
                    libroService.buscarLibroPorTitulo();
                    break;
                case 2:
                    libroService.imprimirLibros();
                    break;
                case 3:
                    autorService.imprimirAutores();
                    break;
                case 4:
                    System.out.println("Escoga el año ");
                    int anio = teclado.nextInt();
                    teclado.nextLine();
                    autorService.imprimirAutoresVivosEn(anio);
                    break;
                case 5:
                    System.out.println("Ingrese el idioma para buscar los libros");
                    System.out.println("es - español");
                    System.out.println("en - ingles");
                    System.out.println("fr - francés");
                    System.out.println("pt - portugués");
                    String subOpcion = teclado.nextLine();
                    libroService.imprimirLibrosPorIdioma(subOpcion);
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
}

