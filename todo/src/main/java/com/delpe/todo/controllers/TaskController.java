package com.delpe.todo.controllers;

import com.delpe.todo.domain.task.Task;
import com.delpe.todo.dtos.task.TaskRequestDTO;
import com.delpe.todo.dtos.task.TaskResponseDTO;
import com.delpe.todo.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(new TaskResponseDTO(task));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_GERENTE')")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        Task newTask = taskService.createTask(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponseDTO(newTask));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_DEV', 'ROLE_GERENTE')")
    @PatchMapping("/{id}/done")
    public ResponseEntity<TaskResponseDTO> patchDone(@PathVariable Long id) {
        Task updatedTask = taskService.patchDone(id);
        return ResponseEntity.ok(new TaskResponseDTO(updatedTask));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_GERENTE')")
    @PatchMapping("/{id}/description")
    public ResponseEntity<TaskResponseDTO> patchDescription(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        Task updatedTask = taskService.patchDescription(id, taskRequestDTO);
        return ResponseEntity.ok(new TaskResponseDTO(updatedTask));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Tarefa deletada com sucesso");
    }
}

