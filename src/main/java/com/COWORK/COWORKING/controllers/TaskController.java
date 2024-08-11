package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.TaskService;
import lombok.AllArgsConstructor;
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

    @PatchMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.updateTask(updateTaskRequest), true));
    }

    @PatchMapping("/assignTask")
    public ResponseEntity<?> assignTask(@RequestBody AssignTaskRequest assignTaskRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.assignTask(assignTaskRequest), true));
    }

    @GetMapping("/viewTask/{taskId}")
    public ResponseEntity<?> viewTask(@PathVariable Long taskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewTask(taskId), true));
    }

    @GetMapping("/viewAllProjectTasks")
    public ResponseEntity<?> viewAllProjectTasks(@RequestParam Long projectId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllProjectTasks(projectId), true));
    }

    @GetMapping("/viewAllUserTasks")
    public ResponseEntity<?> viewAllUserTasks(@RequestParam Long userId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasks(userId), true));
    }

    @GetMapping("/viewAllUserTasksInProject")
    public ResponseEntity<?> viewAllUserTasksInProject(@RequestBody ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasksInProject(viewAllUserTasksInProjectRequest), true));
    }

    @GetMapping("/viewAllUserTasksByDueDate")
    public ResponseEntity<?> viewAllUserTasksByDueDate(@RequestBody ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasksByDueDate(viewAllUserTasksByDueDateRequest), true));
    }

    @GetMapping("/viewAllUserTasksByStatus")
    public ResponseEntity<?> viewAllUserTasksByStatus(@RequestBody ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasksByStatus(viewAllUserTasksByStatusRequest), true));
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.deleteTask(taskId), true));
    }

}
