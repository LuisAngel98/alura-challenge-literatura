package com.alura.literatura.principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
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
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
}

