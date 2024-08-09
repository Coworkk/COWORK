package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(taskService.createTask(createTaskRequest), true));
    }

    @GetMapping("/viewTask/{taskId}")
    public ResponseEntity<?> viewTask(@PathVariable Long taskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewTask(taskId), true));
    }

}
