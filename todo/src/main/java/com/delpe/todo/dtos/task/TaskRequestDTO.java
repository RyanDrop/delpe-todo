package com.delpe.todo.dtos.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(
        @NotBlank @Size(min = 5, max = 100, message = "A descrição da tarefa deve ter entre 5 e 100 caracteres") String description
) {
}
