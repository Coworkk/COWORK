package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.COWORK.COWORKING.data.models.Priority.URGENT;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/database/data.sql"})
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
         objectMapper = new ObjectMapper();
    }

    @Test
    public void createTaskTest() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Design user interface");
        createTaskRequest.setDescription("Develop detailed wireframes and visual mockups for each app screen");
        createTaskRequest.setStartDate(now().plusDays(10));
        createTaskRequest.setDueDate(now().plusDays(20));
        createTaskRequest.setProjectId(200L);
        createTaskRequest.setPriority(URGENT);

        try {
            mockMvc.perform(post("/api/v1/task/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(createTaskRequest))
            ).andExpect(status().isCreated()).andDo(print());
        }
        catch (Exception error) {
            System.out.println(error.getMessage());
        }

    }

    @Test
    public void viewTaskTest() throws Exception {
        try {
            mockMvc.perform(get("/api/v1/task/viewTask/300")
                    .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()).andDo(print());
        }
        catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

}