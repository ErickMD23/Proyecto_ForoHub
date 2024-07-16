package com.alura.foro.api.dto.respuesta;

import com.alura.foro.api.modelo.Respuesta;

public record DatosRetorno(String mensaje, String topico, String autor) {

    public DatosRetorno(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
