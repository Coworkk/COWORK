package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.requests.ProjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void createProject() throws Exception {
        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setStartDate(LocalDateTime.now().plusDays(2));
        projectRequest.setName("project");
        projectRequest.setDescription("description");
        projectRequest.setEndDate(LocalDateTime.now().plusDays(9));
        mockMvc.perform(post("/api/v1/cowork/users/add-project")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(projectRequest))
        ).andExpect(status().isCreated()).andDo(print());
    }
}