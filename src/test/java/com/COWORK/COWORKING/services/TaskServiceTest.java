package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;
import com.COWORK.COWORKING.exceptions.ProjectNotFoundException;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.COWORK.COWORKING.data.models.Priority.URGENT;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void createTaskTest() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Design user interface");
        createTaskRequest.setDescription("Develop detailed wireframes and visual mockups for each app screen");
        createTaskRequest.setStartDate(now());
        createTaskRequest.setDueDate(now()); // change the dates later
        createTaskRequest.setProjectId(200L);
        createTaskRequest.setPriority(URGENT);
        CreateTaskResponse createTaskResponse = taskService.createTask(createTaskRequest);

        assertNotNull(createTaskResponse);
        assertTrue(createTaskResponse.getMessage().contains("success"));
        assertThat(createTaskResponse.getProjectId()).isEqualTo(200L);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void createTask_WithNonExistentProject_ThrowsExceptionTest() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Integrate payment gateway");
        createTaskRequest.setDescription("Set up payment gateway to handle refunds, chargebacks, and transaction disputes");
        createTaskRequest.setStartDate(now());
        createTaskRequest.setDueDate(now()); // change the dates later
        createTaskRequest.setProjectId(1500L);
        createTaskRequest.setPriority(URGENT);

        assertThrows(ProjectNotFoundException.class,()->taskService.createTask(createTaskRequest));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewTaskTest() {
        ViewTaskResponse viewTaskResponse = taskService.viewTask(300L);

        assertThat(viewTaskResponse).isNotNull();
        assertThat(viewTaskResponse.getTaskId()).isEqualTo(300L);
        //add and map project and user
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewNonExistentTask_ThrowsExceptionTest() {
        assertThrows(TaskNotFoundException.class, ()->taskService.viewTask(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllProjectsTasksTest() {
        List<ViewTaskResponse> viewAllProjectTasks = taskService.viewAllProjectTasks(200L);

        assertThat(viewAllProjectTasks).isNotNull();
        assertThat(viewAllProjectTasks.size()).isEqualTo(2);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllNonExistentProjectTasks_ThrowsExceptionTest() {
        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllProjectTasks(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllUserTasksTest() {

    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteTaskTest() {
        String message = taskService.deleteTask(303L);

        assertThat(message).isNotNull();
        assertThat(message).contains("success");
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteNonExistentTask_ThrowsExceptionTest() {
        assertThrows(TaskNotFoundException.class, ()->taskService.deleteTask(1500L));
    }

}