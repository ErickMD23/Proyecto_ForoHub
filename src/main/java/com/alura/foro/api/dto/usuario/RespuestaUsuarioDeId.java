package com.alura.foro.api.dto.usuario;

import com.alura.foro.api.modelo.Usuario;

public record RespuestaUsuarioDeId(String nombre, String email, String contrasena, String tipo) {

    public RespuestaUsuarioDeId(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getTipo().toString());
    }
}
