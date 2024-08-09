package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.SubTaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subtask")
@AllArgsConstructor
public class SubTaskController {

    private final SubTaskService subTaskService;

    @PostMapping("/createSubtask")
    public ResponseEntity<?> createSubTask(@RequestBody CreateSubTaskRequest createSubTaskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(subTaskService.createSubTask(createSubTaskRequest), true));
    }

    @GetMapping("/viewSubTask/{subTaskId}")
    public ResponseEntity<?> viewSubTask(@PathVariable Long subTaskId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(subTaskService.viewSubTask(subTaskId),true));
    }

    @DeleteMapping("/deleteSubTask{subTaskId}")
    public ResponseEntity<?> deleteSubTask(@PathVariable Long subTaskId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(subTaskService.deleteSubTask(subTaskId), true));
    }

}
