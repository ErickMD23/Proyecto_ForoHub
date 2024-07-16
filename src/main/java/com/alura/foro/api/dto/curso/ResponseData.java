package com.alura.foro.api.dto.curso;

import com.alura.foro.api.modelo.Curso;

public record ResponseData(String nombre, String categoria) {

    public ResponseData(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
