package com.yourcompany.taskapi.controller;

import com.yourcompany.taskapi.dto.ServiceResponse;
import com.yourcompany.taskapi.model.Task;
import com.yourcompany.taskapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public ResponseEntity<ServiceResponse<Task>> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        ServiceResponse<Task> response = new ServiceResponse<>("success", createdTask);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<Task>> getTask(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        ServiceResponse<Task> response = new ServiceResponse<>("success", task);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ServiceResponse<List<Task>>> getAllTasks() {
        List<Task> tasks = (List<Task>) taskService.getAllTasks();
        ServiceResponse<List<Task>> response = new ServiceResponse<>("success", tasks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<Task>> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        taskService.updateTask(id, task);
        ServiceResponse<Task> response = new ServiceResponse<>("success", task);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        ServiceResponse<Void> response = new ServiceResponse<>("success", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
