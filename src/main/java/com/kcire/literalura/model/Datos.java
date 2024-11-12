package com.kcire.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos() {
//    @JsonAlias("results") List<DatosLibros> resultados;
}
