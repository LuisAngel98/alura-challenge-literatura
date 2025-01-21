package com.alura.literatura.service;

import com.alura.literatura.dto.LibroDTO;
import com.alura.literatura.model.*;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        //Obtenemos el primer libro de la lista de libros que devuelve la api
        DatosLibro libroBuscado = datos.results().get(0);
        System.out.println(libroBuscado);
        var libro = guardarLibro(libroBuscado);
        System.out.println(libro);////////////////////////////
    }
    public void imprimirLibros(){
        listarLibros().forEach(System.out::println);
    }
    public void imprimirLibrosPorIdioma(String idioma){
        listarLibrosPorIdioma(idioma).forEach(System.out::println);
    }
    public Libro guardarLibro(DatosLibro datosLibro){
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());

        if (libroExistente.isPresent()) {
            // Si el libro ya existe, no lo guardamos y cortamos el flujo
            System.out.println("No se puede registrar el mismo libro mas de una vez");
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
    // Listar libros registrados
    public List<LibroDTO> listarLibros() {
        // Obtenemos todos los libros de la base de datos
        List<Libro> libros = libroRepository.findAll();

        // Convertimos la lista de entidades Libro a DTOs
        return libros.stream()
                .map(libro -> new LibroDTO(
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getIdioma(),
                        libro.getNumeroDeDescargas()
                ))
                .collect(Collectors.toList());
    }
    public List<LibroDTO> listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);

        return libros.stream()
                .map(libro -> new LibroDTO(
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getIdioma(),
                        libro.getNumeroDeDescargas()
                ))
                .collect(Collectors.toList());
    }
}