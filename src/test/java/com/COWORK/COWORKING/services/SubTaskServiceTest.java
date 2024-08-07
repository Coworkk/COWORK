package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;
import com.COWORK.COWORKING.exceptions.SubTaskNotFoundException;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubTaskServiceTest {

    @Autowired
    private SubTaskService subTaskService;

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void createSubTaskTest() {
        CreateSubTaskRequest createSubTaskRequest = new CreateSubTaskRequest();
        createSubTaskRequest.setTitle("");
        createSubTaskRequest.setDescription("");
        createSubTaskRequest.setStartDate(LocalDateTime.now());
        createSubTaskRequest.setDueDate(LocalDateTime.now());
        createSubTaskRequest.setTaskId(300L);
        CreateSubTaskResponse createSubTaskResponse = subTaskService.createSubTask(createSubTaskRequest);

        assertThat(createSubTaskResponse).isNotNull();
        assertTrue(createSubTaskResponse.getMessage().contains("success"));
        assertThat(createSubTaskResponse.getTaskId()).isEqualTo(300L);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void createSubTask_WithNonExistentTask_ThrowsExceptionTest() {
        CreateSubTaskRequest createSubTaskRequest = new CreateSubTaskRequest();
        createSubTaskRequest.setTitle("");
        createSubTaskRequest.setDescription("");
        createSubTaskRequest.setStartDate(LocalDateTime.now());
        createSubTaskRequest.setDueDate(LocalDateTime.now());
        createSubTaskRequest.setTaskId(1500L);

        assertThrows(TaskNotFoundException.class, ()->subTaskService.createSubTask(createSubTaskRequest));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewSubTaskTest() {
        ViewSubTaskResponse viewSubTaskResponse = subTaskService.viewSubTask(400L);

        assertThat(viewSubTaskResponse).isNotNull();
        assertThat(viewSubTaskResponse.getSubTaskId()).isEqualTo(400L);
        assertThat(viewSubTaskResponse.getTaskId()).isEqualTo(300L);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewNonExistentSubTask_ThrowsExceptionTest() {
        assertThrows(SubTaskNotFoundException.class, ()->subTaskService.viewSubTask(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteSubTaskTest() {
        String message = subTaskService.deleteSubTask(403L);

        assertThat(message).isNotNull();
        assertTrue(message.contains("success"));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteNonExistentSubTask_ThrowsExceptionTest() {
        assertThrows(SubTaskNotFoundException.class, ()->subTaskService.deleteSubTask(1500L));
    }

}