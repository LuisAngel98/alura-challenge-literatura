# Proyecto de Reto: Literatura - Alura Latam Oracle

Este proyecto es parte de un reto de Alura Latam Oracle, que consiste en construir una API REST utilizando Spring Boot para gestionar información sobre libros y autores, obtenida de una API externa (Gutenberg). El objetivo es desarrollar una aplicación que permita almacenar y consultar libros, autores, y relacionarlos adecuadamente en una base de datos.

## Características

- **Consumo de API Externa (Gutenberg)**: Se obtiene información de libros y autores para almacenarlos en la base de datos.
- **Entidades**:
  - **Autor**: Guarda información sobre los autores, incluyendo nombre, fecha de nacimiento y fecha de fallecimiento.
  - **Libro**: Guarda información sobre los libros, incluyendo título, idioma, número de descargas y el autor.
- **Relación**: Un autor puede tener varios libros (relación 1 a muchos).
- **Filtros**:
  - Buscar un libro en la api y napearla a un objeto dentro de mi aplicación.
  - Listado de libros con información básica (título, autor, idioma y descargas).
  - Listado de autores con sus libros asociados.
  - Listado de autores vivos en una fecha específica.
  - Listado de libros por idioma.

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal para la creación de la API REST.
- **JPA**: Para la gestión de la base de datos.
- **H2 Database**: Base de datos en memoria para almacenar los datos de libros y autores.
- **Maven**: Gestión de dependencias y construcción del proyecto.

## Requisitos

- JDK 11 o superior
- Maven

