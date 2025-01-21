package com.alura.literatura.dto;

import java.util.List;

public record AutorDTO(
        String nombre,
        int fechaNacimiento,
        int fechaFallecimiento,
        List<String> libros
) {
    @Override
    public String toString() {
        return """
                 Autor: %s
                 Fecha de Nacimiento: %d
                 Fecha de Fallecimiento: %d
                 Libros: %s
               """.formatted(nombre, fechaNacimiento, fechaFallecimiento, libros);
    }
}
