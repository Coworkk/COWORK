package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.COWORK.COWORKING.data.models.Priority.URGENT;
import static com.COWORK.COWORKING.data.models.Status.NOT_STARTED;
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
            mockMvc.perform(post("/api/v1/cowork/users/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(createTaskRequest))
            ).andExpect(status().isCreated()).andDo(print());

    }

    @Test
    public void updateTask() throws Exception {

    }

    @Test
    public void assignTask() throws Exception {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setUserId("");
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
        viewAllUserTasksInProjectRequest.setUserId("");
        viewAllUserTasksInProjectRequest.setProjectId(200L);

        mockMvc.perform(get("/api/v1/task/viewAllUserTasksInProject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(viewAllUserTasksInProjectRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserTasksByDueDate() throws Exception {
        ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest = new ViewAllUserTasksByDueDateRequest();
        viewAllUserTasksByDueDateRequest.setUserId("");
        viewAllUserTasksByDueDateRequest.setDueDate(LocalDateTime.parse("2024-09-09 09:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        mockMvc.perform(get("/api/v1/task/viewAllUserTasksByDueDate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(viewAllUserTasksByDueDateRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserTasksByStatus() throws Exception {
        ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest = new ViewAllUserTasksByStatusRequest();
        viewAllUserTasksByStatusRequest.setStatus(NOT_STARTED);
        viewAllUserTasksByStatusRequest.setUserId("");

        mockMvc.perform(get("api/v1/task/viewAllUserTasksByStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(viewAllUserTasksByStatusRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void deleteTaskTest() throws Exception {
        mockMvc.perform(delete("/api/v1/task/deleteTask/302")
                .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk()).andDo(print());
    }

}