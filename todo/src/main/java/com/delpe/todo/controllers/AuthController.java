package com.delpe.todo.controllers;

import com.delpe.todo.dtos.auth.AuthRequestDTO;
import com.delpe.todo.dtos.auth.AuthResponseDTO;
import com.delpe.todo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@RequestBody AuthRequestDTO loginUserDto) {
        AuthResponseDTO token = authService.login(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
