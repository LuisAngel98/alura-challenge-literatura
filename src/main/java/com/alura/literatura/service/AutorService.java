package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.DatosAutor;
import com.alura.literatura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private IAutorRepository autorRepository;

    public void guardarAutor(DatosAutor datosAutor) {
        Optional<Autor> autorExistente = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre());

        if (autorExistente.isEmpty()) {
            Autor autor = new Autor(datosAutor);
            autorRepository.save(autor);
        } else {
            return;
        }
    }
    public void guardarAutores(List<DatosAutor> listaDatosAutores) {
        for (DatosAutor datosAutor : listaDatosAutores) {
            guardarAutor(datosAutor);
        }
    }
}
