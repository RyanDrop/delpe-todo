package com.delpe.todo.services;

import com.delpe.todo.config.auth.JwtService;
import com.delpe.todo.config.userDetails.UserDetailsConfig;
import com.delpe.todo.dtos.auth.AuthRequestDTO;
import com.delpe.todo.dtos.auth.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO login(AuthRequestDTO loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsConfig userDetails = (UserDetailsConfig) authentication.getPrincipal();
        return new AuthResponseDTO(jwtService.generateToken(userDetails));
    }
}
