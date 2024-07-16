package com.alura.foro.api.dto.topico;

import com.alura.foro.api.dto.curso.ResponseData;
import com.alura.foro.api.dto.usuario.RespuestaUsuario;
import com.alura.foro.api.modelo.Topico;

public record RespuestaTopicoId(Long id, String titulo, String mensaje, String fechaCreacion, String estado, RespuestaUsuario autor,
                                ResponseData curso) {

    public RespuestaTopicoId(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(), new RespuestaUsuario(topico.getAutor()),
                new ResponseData(topico.getCurso()));
    }
}
