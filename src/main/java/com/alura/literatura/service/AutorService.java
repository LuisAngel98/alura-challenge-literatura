package com.alura.literatura.service;

import com.alura.literatura.dto.AutorDTO;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.DatosAutor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private IAutorRepository autorRepository;

    public void imprimirAutores(){
        listarAutores().forEach(System.out::println);
    }
    public void imprimirAutoresVivosEn(int anio){
        listarAutoresVivosEn(anio).forEach(System.out::println);
    }
    public List<AutorDTO> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .map(autor -> new AutorDTO(
                        autor.getNombre(),
                        autor.getFechaNacimiento(),
                        autor.getFechaFallecimiento(),
                        autor.getLibros().stream()  // Obtenemos los títulos de sus libros
                                .map(Libro::getTitulo)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
    public List<AutorDTO> listarAutoresVivosEn(int anio) {
        List<Autor> autores = autorRepository.findAutoresVivosEn(anio);
        return autores.stream()
                .map(autor -> new AutorDTO(
                        autor.getNombre(),
                        autor.getFechaNacimiento(),
                        autor.getFechaFallecimiento(),
                        autor.getLibros().stream()  // Obtenemos los títulos de sus libros
                                .map(Libro::getTitulo)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
