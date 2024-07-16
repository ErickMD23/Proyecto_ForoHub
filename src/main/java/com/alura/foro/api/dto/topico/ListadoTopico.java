package com.alura.foro.api.dto.topico;

import com.alura.foro.api.modelo.Topico;

public record ListadoTopico(Long id, String titulo, String mensaje, String autor, String curso) {

    public ListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }
}
