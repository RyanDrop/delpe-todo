package com.delpe.todo.dtos.task;

import com.delpe.todo.domain.task.Task;
import com.delpe.todo.dtos.user.UserResponseDTO;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String description,
        Boolean done,
        UserResponseDTO createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public TaskResponseDTO(Task task) {
        this(task.getId(),
                task.getDescription(),
                task.getDone(),
                task.getCreatedBy() != null ? new UserResponseDTO(task.getCreatedBy()) : null,
                task.getCreatedAt(),
                task.getUpdatedAt());
    }
}
