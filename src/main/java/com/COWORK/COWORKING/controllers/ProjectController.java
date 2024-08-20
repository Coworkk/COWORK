package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AddCommentRequest;
import com.COWORK.COWORKING.dtos.requests.ProjectRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.impl.ProjectServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@RestController
@RequestMapping("/api/v1/cowork")
@AllArgsConstructor
public class ProjectController {

    private final ProjectServicesImpl projectServices;
    @PostMapping(value = "users/add-project",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProject(@RequestBody ProjectRequest addProjectRequest) {
        try {
            return ResponseEntity.status(CREATED)
                    .body(new ApiResponse(projectServices.createProject(addProjectRequest), true));
        }catch (Exception e){
            return ResponseEntity.status(EXPECTATION_FAILED)
                    .body(new ApiResponse("something went wrong ", false));
        }
    }
}
