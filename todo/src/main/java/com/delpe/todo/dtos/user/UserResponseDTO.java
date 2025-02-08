package com.delpe.todo.dtos.user;

import com.delpe.todo.domain.user.User;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String lastName,
        String email,
        String accessLevel,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getAccessLevel().getDisplayName(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
