package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.dtos.requests.ProjectRequest;
import com.COWORK.COWORKING.dtos.responses.ProjectResponse;

public interface ProjectService {

    Project findProjectById(Long projectId);
    ProjectResponse createProject(ProjectRequest projectRequest);
}
