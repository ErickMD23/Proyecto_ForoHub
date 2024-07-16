package com.alura.foro.api.dto.usuario;

import com.alura.foro.api.modelo.Usuario;

public record RespuestaUsuario(String nombre, String email, String tipo) {

    public RespuestaUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
