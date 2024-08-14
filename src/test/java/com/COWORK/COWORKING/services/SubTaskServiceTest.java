package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserSubTasksByStatusRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTaskSubTasksRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;
import com.COWORK.COWORKING.exceptions.SubTaskNotFoundException;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static com.COWORK.COWORKING.data.models.Status.COMPLETED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = {"/database/data.sql"})
public class SubTaskServiceTest {

    @Autowired
    private SubTaskService subTaskService;

    @Test
    public void createSubTaskTest() {
        CreateSubTaskRequest createSubTaskRequest = new CreateSubTaskRequest();
        createSubTaskRequest.setTitle("");
        createSubTaskRequest.setDescription("");
        createSubTaskRequest.setStartDate(LocalDateTime.now().plusDays(10));
        createSubTaskRequest.setDueDate(LocalDateTime.now().plusDays(20));
        createSubTaskRequest.setTaskId(300L);
        CreateSubTaskResponse createSubTaskResponse = subTaskService.createSubTask(createSubTaskRequest);
        assertThat(createSubTaskResponse).isNotNull();
        assertTrue(createSubTaskResponse.getMessage().contains("success"));
        assertThat(createSubTaskResponse.getTaskId()).isEqualTo(300L);
    }

    @Test
    public void createSubTask_WithNonExistentTask_ThrowsExceptionTest() {
        CreateSubTaskRequest createSubTaskRequest = new CreateSubTaskRequest();
        createSubTaskRequest.setTitle("");
        createSubTaskRequest.setDescription("");
        createSubTaskRequest.setStartDate(LocalDateTime.now().plusDays(10));
        createSubTaskRequest.setDueDate(LocalDateTime.now().plusDays(20));
        createSubTaskRequest.setTaskId(1500L);
        assertThrows(TaskNotFoundException.class, ()->subTaskService.createSubTask(createSubTaskRequest));
    }

    @Test
    public void viewSubTaskTest() {
        ViewSubTaskResponse viewSubTaskResponse = subTaskService.viewSubTask(400L);
        assertThat(viewSubTaskResponse).isNotNull();
        assertThat(viewSubTaskResponse.getSubTaskId()).isEqualTo(400L);
        assertThat(viewSubTaskResponse.getTaskId()).isEqualTo(300L);
    }

    @Test
    public void viewNonExistentSubTask_ThrowsExceptionTest() {
        assertThrows(SubTaskNotFoundException.class, ()->subTaskService.viewSubTask(1500L));
    }

    @Test
    public void viewAllUserSubTasksTest() {
        List<ViewSubTaskResponse> allUserSubTasks = subTaskService.viewAllUserSubTasks("f62f68e8-023f-4c67-9e87-7af2a111e5eb");

        assertThat(allUserSubTasks).isNotNull();
        assertThat(allUserSubTasks.size()).isEqualTo(3);
    }

    @Test
    public void viewAllUserTaskSubTasksTest() {
        ViewAllUserTaskSubTasksRequest viewAllUserTaskSubTasksRequest = new ViewAllUserTaskSubTasksRequest();
        viewAllUserTaskSubTasksRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        viewAllUserTaskSubTasksRequest.setTaskId(300L);
        List<ViewSubTaskResponse> allUserTasksSubTasks = subTaskService.viewAllUserTaskSubTasks(viewAllUserTaskSubTasksRequest);

        assertThat(allUserTasksSubTasks).isNotNull();
        assertThat(allUserTasksSubTasks.size()).isEqualTo(2);
    }

    @Test
    public void viewAllUserSubTasksByStatusTest() {
        ViewAllUserSubTasksByStatusRequest viewAllUserSubTasksByStatusRequest = new ViewAllUserSubTasksByStatusRequest();
        viewAllUserSubTasksByStatusRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        viewAllUserSubTasksByStatusRequest.setStatus(COMPLETED);
        List<ViewSubTaskResponse> allUserSubTasks = subTaskService.viewAllUserSubTasksByStatus(viewAllUserSubTasksByStatusRequest);
        assertThat(allUserSubTasks).isNotNull();
        assertThat(allUserSubTasks.size()).isEqualTo(1);
    }

    @Test
    public void deleteSubTaskTest() {
        String message = subTaskService.deleteSubTask(402L);
        assertThat(message).isNotNull();
        assertTrue(message.contains("success"));
    }

    @Test
    public void deleteNonExistentSubTask_ThrowsExceptionTest() {
        assertThrows(SubTaskNotFoundException.class, ()->subTaskService.deleteSubTask(1500L));
    }

}