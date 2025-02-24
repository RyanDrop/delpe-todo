package com.delpe.todo.services;

import com.delpe.todo.config.SecurityConfig;
import com.delpe.todo.domain.task.Task;
import com.delpe.todo.domain.user.User;
import com.delpe.todo.dtos.task.TaskRequestDTO;
import com.delpe.todo.dtos.task.TaskResponseDTO;
import com.delpe.todo.repositories.TaskRepository;
import com.delpe.todo.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa com ID " + id + " não encontrada"));
    }

    public List<TaskResponseDTO> getAllTasks() {
        Stream<TaskResponseDTO> mapTaskToResponseDTO = taskRepository.findAll().stream().map(TaskResponseDTO::new);
        List<TaskResponseDTO> response = mapTaskToResponseDTO.toList();
        return response;
    }

    public Task createTask(TaskRequestDTO taskRequestDTO) {
        Task.TaskBuilder builderTask = Task.builder();
        builderTask.description(taskRequestDTO.description());
        builderTask.done(false);

        String loggedUserEmail = SecurityConfig.getAuthenticatedUsername();
        if (loggedUserEmail != null) {
            User loggedUser = userRepository.findByEmail(loggedUserEmail)
                    .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));

            builderTask.createdBy(loggedUser);
        } else {
            throw new RuntimeException("Usuário não autenticado");
        }

        Task newTask = builderTask.build();
        return taskRepository.save(newTask);
    }

    public Task patchDone(Long id) {
        Task task = getTaskById(id);
        task.setDone(!task.getDone());
        return taskRepository.save(task);
    }

    public Task patchDescription(Long id, @Valid TaskRequestDTO taskRequestDTO) {
        Task task = getTaskById(id);
        task.setDescription(taskRequestDTO.description());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
