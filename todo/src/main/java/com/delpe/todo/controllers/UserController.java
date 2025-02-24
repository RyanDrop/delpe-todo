package com.delpe.todo.controllers;

import com.delpe.todo.domain.user.AccessLevel;
import com.delpe.todo.domain.user.User;
import com.delpe.todo.dtos.user.UserRequestDTO;
import com.delpe.todo.dtos.user.UserResponseDTO;
import com.delpe.todo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        List<UserResponseDTO> users = userService.getAllUsers();
        ResponseEntity<List<UserResponseDTO>> response = users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
        return response;
    }

    @GetMapping("/access-level/{accessLevel}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByAccessLevel(@PathVariable AccessLevel accessLevel) {
        List<UserResponseDTO> users = userService.getUsersByAccessLevel(accessLevel);
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userDTO) {
        User newUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(newUser));
    }
}
