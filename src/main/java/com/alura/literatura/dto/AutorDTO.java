package com.alura.literatura.dto;

import java.util.List;

public record AutorDTO(
        String nombre,
        int fechaNacimiento,
        int fechaFallecimiento,
        List<String> libros
) {}
