package com.alura.foro.api.dto.respuesta;

import com.alura.foro.api.modelo.Respuesta;

public record DatosListado(Long id, String mensaje, String topico, String autor) {

    public DatosListado(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
