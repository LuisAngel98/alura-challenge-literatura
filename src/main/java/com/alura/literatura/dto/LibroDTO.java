package com.alura.literatura.dto;

public record LibroDTO(
        String titulo,
        String autorNombre,
        String idioma,
        int numeroDeDescargas
) {
    @Override
    public String toString() {
        return """
                 ---- LIBRO ----
                 Título: %s
                 Autor: %s
                 Idioma: %s
                 Numero de descargas: %d
                 ---------------
               """.formatted(titulo, autorNombre, idioma, numeroDeDescargas);
    }
}
