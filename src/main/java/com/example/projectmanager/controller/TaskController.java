package com.example.projectmanager.controller;

import com.example.projectmanager.model.Task;
import com.example.projectmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setCreatedAt(LocalDateTime.now());
        if (task.getStatus() == null) {
            task.setStatus("TODO");
        }
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
