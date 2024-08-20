package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.dtos.requests.ProjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectServicesImplTest {

    @Autowired
    private ProjectServicesImpl projectServices;
    @Test
    void findProjectById() {

    }

    @Test
    void createProject() {
        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setStartDate(LocalDateTime.now().plusDays(2));
        projectRequest.setName("project");
        projectRequest.setDescription("description");
        projectRequest.setEndDate(LocalDateTime.now().plusDays(9));
        var response =projectServices.createProject(projectRequest);
        assertThat(response.toString()).isNotNull();
    }
}