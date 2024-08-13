package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.repositories.ProjectRepository;
import com.COWORK.COWORKING.exceptions.ProjectNotFoundException;
import com.COWORK.COWORKING.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(()->new ProjectNotFoundException
                        (String.format("Project with id %d not found", projectId)));
    }
}
