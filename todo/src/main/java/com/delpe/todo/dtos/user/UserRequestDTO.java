package com.delpe.todo.dtos.user;

import com.delpe.todo.domain.user.AccessLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres") String name,
        @NotBlank @Size(min = 3, max = 50, message = "O sobrenome deve ter entre 3 e 50 caracteres") String lastName,
        @NotBlank @Email(message = "Insira um e-mail válido") String email,
        @NotBlank @Size(min = 8, message = "Sua senha deve ter no mínimo 8 caracteres") String password,
        @NotNull AccessLevel accessLevel
) {
}


