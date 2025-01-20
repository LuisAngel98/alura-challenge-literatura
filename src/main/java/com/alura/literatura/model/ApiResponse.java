package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        @JsonAlias("count") int total,
        @JsonAlias("next") String nextPage,
        @JsonAlias("previous") String previousPage,
        @JsonAlias("results") List<DatosLibro> results
) {}
