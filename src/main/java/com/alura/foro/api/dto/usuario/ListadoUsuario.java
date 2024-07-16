package com.alura.foro.api.dto.usuario;

import com.alura.foro.api.modelo.Usuario;

public record ListadoUsuario(Long id, String nombre, String email, String tipo) {

    public ListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
