package com.alura.foro.api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacionDeUsuario(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena) {
}
