package com.delpe.todo.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDTO(
        @NotBlank @Email(message = "Insira um e-mail válido") String email,
        @NotBlank @Size(min = 8, message = "Sua senha deve ter no mínimo 8 caracteres") String password
) {
}
