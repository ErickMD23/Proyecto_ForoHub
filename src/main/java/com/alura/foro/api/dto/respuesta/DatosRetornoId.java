package com.alura.foro.api.dto.respuesta;

import com.alura.foro.api.dto.topico.RespuestaTopico;
import com.alura.foro.api.dto.usuario.RespuestaUsuario;
import com.alura.foro.api.modelo.Respuesta;

public record DatosRetornoId(String mensaje, RespuestaTopico topico, String fechaCreacion, RespuestaUsuario autor, String solucion) {

    public DatosRetornoId(Respuesta respuesta) {
        this(respuesta.getMensaje(), new RespuestaTopico(respuesta.getTopico()), respuesta.getFechaCreacion().toString(),
                new RespuestaUsuario(respuesta.getAutor()), respuesta.getSolucion().toString());
    }
}
