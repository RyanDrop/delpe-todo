package com.delpe.todo.services;

import com.delpe.todo.domain.user.AccessLevel;
import com.delpe.todo.domain.user.User;
import com.delpe.todo.dtos.user.UserRequestDTO;
import com.delpe.todo.dtos.user.UserResponseDTO;
import com.delpe.todo.exception.UserNotFoundException;
import com.delpe.todo.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        Stream<UserResponseDTO> mapUserToResponseDTO = userRepository.findAll().stream().map(UserResponseDTO::new);
        List<UserResponseDTO> response = mapUserToResponseDTO.toList();
        return response;
    }

    public User createUser(UserRequestDTO userRequestDTO) {
        User user = objectMapper.convertValue(userRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        return userRepository.save(user);
    }

    public List<UserResponseDTO> getUsersByAccessLevel(AccessLevel accessLevel) {
        Stream<UserResponseDTO> mapUserToResponseDTO = userRepository.findAllByAccessLevel(accessLevel).stream().map(UserResponseDTO::new);
        List<UserResponseDTO> response = mapUserToResponseDTO.toList();
        return response;
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado"));
        return new UserResponseDTO(user);
    }

}
