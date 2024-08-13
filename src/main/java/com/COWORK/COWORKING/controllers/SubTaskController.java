package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserSubTasksByStatusRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTaskSubTasksRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.SubTaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/cowork")
@AllArgsConstructor
public class SubTaskController {

    private final SubTaskService subTaskService;

    @PostMapping("users/createSubtask")
    public ResponseEntity<?> createSubTask(@RequestBody CreateSubTaskRequest createSubTaskRequest) {
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(subTaskService.createSubTask(createSubTaskRequest), true));
    }

//    @PatchMapping("/updateSubTask")
//    public ResponseEntity<?> subTask() {
//        return ResponseEntity.status(OK)
//                .body(new ApiResponse(subTaskService.updateSubTask(), true));
//    }

    @GetMapping("users/viewSubTask/{subTaskId}")
    public ResponseEntity<?> viewSubTask(@PathVariable Long subTaskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(subTaskService.viewSubTask(subTaskId),true));
    }

    @GetMapping("users/viewAllUserSubTasks")
    public ResponseEntity<?> viewAllUserSubTasks(@RequestParam Long userId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(subTaskService.viewAllUserSubTasks(userId), true));
    }

    @GetMapping("users/viewAllUserTaskSubTasks")
    public ResponseEntity<?> viewAllUserTaskSubTasks(@RequestBody ViewAllUserTaskSubTasksRequest viewAllUserTaskSubTasksRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(subTaskService.viewAllUserTaskSubTasks(viewAllUserTaskSubTasksRequest), true));
    }

    @GetMapping("users/viewAllUserSubTasksByStatus")
    public ResponseEntity<?> viewAllUserSubTasksByStatus(@RequestBody ViewAllUserSubTasksByStatusRequest viewAllUserSubTasksByStatusRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(subTaskService.viewAllUserSubTasksByStatus(viewAllUserSubTasksByStatusRequest), true));
    }

    @DeleteMapping("users/deleteSubTask{subTaskId}")
    public ResponseEntity<?> deleteSubTask(@PathVariable Long subTaskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(subTaskService.deleteSubTask(subTaskId), true));
    }

}
