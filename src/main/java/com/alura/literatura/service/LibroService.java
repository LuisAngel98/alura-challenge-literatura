package com.alura.literatura.service;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private IAutorRepository autorRepository;
    @Autowired
    private ILibroRepository libroRepository;

    public void buscarLibroPorTitulo() {
        System.out.println("Ingresar el nombre del libro que desea buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        ApiResponse datos = conversor.obtenerDatos(json, ApiResponse.class);
        if (datos.results().isEmpty())
        {
            System.out.println("Libro no encontrado....");
            return;
        }
        DatosLibro libroBuscado = datos.results().get(0);
        System.out.println(libroBuscado);
//        DatosAutor datosAutor = libroBuscado.autores().get(0);
//
//        Optional<Libro> libroExiste = libroRepository.findByTituloContainsIgnoreCase(libroBuscado.titulo());
//        if (libroExiste.isPresent()){
//            System.out.println("El libro ya se encuentra en la Base de Datos!");
//            System.out.println(libroExiste);
//            return;
//        }
//        Autor autorExiste = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre());
//        if (autorExiste != null){
//            var libro = guardarLibro(libroBuscado, autorExiste);
//            libroRepository.save(libro);
//            System.out.println("---------- LIBRO REGISTRADO ----------");
//            System.out.println(libro);
//        }
//        else {
//            //Acción de registro desde 0
//            Autor autor = new Autor(datosAutor);
//            autor = autorRepositorio.save(autor);
//            libro = registrarLibroEnBD(datosLibro, autor);
//            libroRepositorio.save(libro);
//            System.out.println("---------- LIBRO REGISTRADO ----------");
//            System.out.println(libro);
//        }
    }
    public Libro guardarLibro(DatosLibro datosLibro, Autor autor){
        if(autor != null){
            return new Libro(datosLibro, autor);
        }
        else {
            System.out.println("El campo 'autor' se encuentra vacío, no se puede crear el Libro ");
            return null;
        }
    }
}
