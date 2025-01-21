package com.alura.literatura.service;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private AutorService autorService;
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
        var libro = guardarLibro(libroBuscado);
        if (libro != null){
            System.out.println("Libro ya existe");
        }
    }
    public Libro guardarLibro(DatosLibro datosLibro){
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());

        if (libroExistente.isPresent()) {
            // Si el libro ya existe, no lo guardamos y cortamos el flujo
            return libroExistente.get();
        }
        // Obtener o crear el primer autor de la lista de autores
        Autor autor = obtenerAutor(datosLibro.autores().get(0));
        Libro libro = new Libro(datosLibro);
        libro.setAutor(autor);

        return libroRepository.save(libro);
    }
    private Autor obtenerAutor(DatosAutor datosAutor) {
        Optional<Autor> autorExistente = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre());

        // Si existe, lo devolvemos; si no, lo guardamos
        return autorExistente.orElseGet(() -> autorRepository.save(new Autor(datosAutor)));
    }
    public void guardarLibros(List<DatosLibro> listaDatosLibros) {
        for (DatosLibro datosLibro : listaDatosLibros) {
            guardarLibro(datosLibro);  // Guarda el libro si no existe
        }
    }
}