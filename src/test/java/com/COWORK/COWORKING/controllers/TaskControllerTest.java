package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AssignTaskRequest;
import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTasksInProjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.COWORK.COWORKING.data.models.Priority.URGENT;
import static java.time.LocalDateTime.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void createTaskTest() throws Exception {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Design user interface");
        createTaskRequest.setDescription("Develop detailed wireframes and visual mockups for each app screen");
        createTaskRequest.setStartDate(now().plusDays(10));
        createTaskRequest.setDueDate(now().plusDays(20));
        createTaskRequest.setProjectId(200L);
        createTaskRequest.setPriority(URGENT);

            mockMvc.perform(post("/api/v1/task/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(createTaskRequest))
            ).andExpect(status().isCreated()).andDo(print());

    }

    @Test
    public void assignTask() throws Exception {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setUserId(100L);
        assignTaskRequest.setTaskId(300L);

        mockMvc.perform(patch("/api/v1/task/assignTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(assignTaskRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewTaskTest() throws Exception {
            mockMvc.perform(get("/api/v1/task/viewTask/300")
                    .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllProjectTasksTest() throws Exception {
        mockMvc.perform(get("/api/v1/task/viewAllProjectTasks?projectId=200")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserTasksTest() throws Exception {
        mockMvc.perform(get("/api/v1/task/viewAllUserTasks?userId=100")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserTasksInProject() throws Exception {
        ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest = new ViewAllUserTasksInProjectRequest();
        viewAllUserTasksInProjectRequest.setUserId(100L);
        viewAllUserTasksInProjectRequest.setProjectId(200L);

        mockMvc.perform(get("/api/v1/task/viewAllUserTasksInProject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(viewAllUserTasksInProjectRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void deleteTaskTest() throws Exception {
        mockMvc.perform(delete("/api/v1/task/deleteTask/302")
                .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk()).andDo(print());
    }

}