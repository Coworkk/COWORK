package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserSubTasksByStatusRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTaskSubTasksRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.COWORK.COWORKING.data.models.Status.COMPLETED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/database/data.sql"})
public class SubTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void createSubTaskTest() throws Exception {
        CreateSubTaskRequest createSubTaskRequest = new CreateSubTaskRequest();
        createSubTaskRequest.setTitle("title");
        createSubTaskRequest.setDescription("description");
        createSubTaskRequest.setStartDate(LocalDateTime.now().plusDays(10));
        createSubTaskRequest.setDueDate(LocalDateTime.now().plusDays(20));
        createSubTaskRequest.setTaskId(300L);
        mockMvc.perform(post("/api/v1/cowork/users/createSubtask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createSubTaskRequest))
        ).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void viewSubTaskTest() throws Exception {
        mockMvc.perform(get("/api/v1/cowork/users/viewSubTask/400")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserSubTasksTest() throws Exception {
        mockMvc.perform(get("/api/v1/cowork/users/viewAllUserSubTasks?userId=f62f68e8-023f-4c67-9e87-7af2a111e5eb")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserTaskSubTasksTest() throws Exception {
        ViewAllUserTaskSubTasksRequest viewAllUserTaskSubTasksRequest = new ViewAllUserTaskSubTasksRequest();
        viewAllUserTaskSubTasksRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        viewAllUserTaskSubTasksRequest.setTaskId(300L);
        mockMvc.perform(get("/api/v1/cowork/users/viewAllUserTaskSubTasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(viewAllUserTaskSubTasksRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserSubTasksByStatusTest() throws Exception {
        ViewAllUserSubTasksByStatusRequest viewAllUserSubTasksByStatusRequest = new ViewAllUserSubTasksByStatusRequest();
        viewAllUserSubTasksByStatusRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        viewAllUserSubTasksByStatusRequest.setStatus(COMPLETED);
        mockMvc.perform(get("/api/v1/cowork/users/viewAllUserSubTasksByStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(viewAllUserSubTasksByStatusRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void deleteTaskTest() throws Exception {
        mockMvc.perform(delete("/api/v1/cowork/users/deleteSubTask/402")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

}