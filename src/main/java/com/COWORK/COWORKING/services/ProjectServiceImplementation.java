package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.repositories.ProjectRepository;
import com.COWORK.COWORKING.exceptions.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService{

    private final ProjectRepository projectRepository;

    @Override
    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(()->new ProjectNotFoundException
                        (String.format("Project with %d not found", projectId)));
    }
}
