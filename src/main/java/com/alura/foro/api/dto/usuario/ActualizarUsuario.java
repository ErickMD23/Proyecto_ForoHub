package com.alura.foro.api.dto.usuario;

import com.alura.foro.api.modelo.Tipo;

public record ActualizarUsuario(Long id, String nombre, String email, String contrasena, Tipo tipo) {
}
