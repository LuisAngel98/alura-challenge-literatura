package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombreContainsIgnoreCase(String nombre);
    List<Autor> findByFechaNacimientoGreaterThanEqualAndFechaFallecimientoLessThanEqual(int fecha_nacimiento, int fecha_fallecimiento);
}
