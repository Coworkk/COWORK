package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.repositories.ProjectRepository;
import com.COWORK.COWORKING.dtos.requests.ProjectRequest;
import com.COWORK.COWORKING.dtos.responses.ProjectResponse;
import com.COWORK.COWORKING.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServicesImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(()->new RuntimeException("project not found"));
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
           Project project = new Project();
           project.setDescription(projectRequest.getDescription());
           project.setName(projectRequest.getName());
           project.setEndDate(projectRequest.getEndDate());
           project.setStartDate(projectRequest.getStartDate());
           project=projectRepository.save(project);
           return new ModelMapper().map(project, ProjectResponse.class);
    }
}
