package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/cowork")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("users/createTask")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(taskService.createTask(createTaskRequest), true));
    }

    @PatchMapping("users/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.updateTask(updateTaskRequest), true));
    }

    @PatchMapping("users/assignTask")
    public ResponseEntity<?> assignTask(@RequestBody AssignTaskRequest assignTaskRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.assignTask(assignTaskRequest), true));
    }

    @GetMapping("users/viewTask/{taskId}")
    public ResponseEntity<?> viewTask(@PathVariable Long taskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewTask(taskId), true));
    }

    @GetMapping("users/viewAllProjectTasks")
    public ResponseEntity<?> viewAllProjectTasks(@RequestParam Long projectId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllProjectTasks(projectId), true));
    }

    @GetMapping("users/viewAllUserTasks")
    public ResponseEntity<?> viewAllUserTasks(@RequestParam String userId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasks(userId), true));
    }

    @GetMapping("users/viewAllUserTasksInProject")
    public ResponseEntity<?> viewAllUserTasksInProject(@RequestBody ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasksInProject(viewAllUserTasksInProjectRequest), true));
    }

    @GetMapping("users/viewAllUserTasksByDueDate")
    public ResponseEntity<?> viewAllUserTasksByDueDate(@RequestBody ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasksByDueDate(viewAllUserTasksByDueDateRequest), true));
    }

    @GetMapping("users/viewAllUserTasksByStatus")
    public ResponseEntity<?> viewAllUserTasksByStatus(@RequestBody ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.viewAllUserTasksByStatus(viewAllUserTasksByStatusRequest), true));
    }

    @DeleteMapping("users/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(taskService.deleteTask(taskId), true));
    }

}
