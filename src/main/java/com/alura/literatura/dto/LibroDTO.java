package com.alura.literatura.dto;

public record LibroDTO(
        String titulo,
        String autorNombre,
        String idioma,
        int numeroDeDescargas
) {}
