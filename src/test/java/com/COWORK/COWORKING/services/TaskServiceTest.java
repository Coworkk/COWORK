package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AssignTaskRequest;
import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.requests.UpdateTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTasksInProjectRequest;
import com.COWORK.COWORKING.dtos.responses.AssignTaskResponse;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.UpdateTaskResponse;
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
    public void updateTaskTest() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setTaskId(300L);
        updateTaskRequest.setTitle("blaa");
        UpdateTaskResponse updateTaskResponse = taskService.updateTask(updateTaskRequest);

        assertThat(updateTaskResponse).isNotNull();
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void updateNonExistentTask_ThrowsExceptionTest() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setTaskId(300L);
        updateTaskRequest.setTitle("blaa");
        updateTaskRequest.setDescription("dba");
        // Throw user not found exception
        assertThrows(TaskNotFoundException.class, ()->taskService.updateTask(updateTaskRequest));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void assignTaskTest() {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setUserId(100L);
        assignTaskRequest.setTaskId(300L);
        AssignTaskResponse assignTaskResponse = taskService.assignTask(assignTaskRequest);

        assertThat(assignTaskResponse).isNotNull();
        assertThat(assignTaskResponse.getTaskId()).isEqualTo(300L);
        assertThat(assignTaskResponse.getUserId()).isEqualTo(100L);
    }

    @Test
    @Sql(scripts ={"/database/data.sql"})
    public void assignNonExistentTaskToUser_ThrowsExceptionTest() {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setTaskId(1500L);
        assignTaskRequest.setUserId(100L);

        assertThrows(TaskNotFoundException.class ,()->taskService.assignTask(assignTaskRequest));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void assignTaskToNonExistentUser_ThrowsExceptionTest() {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setTaskId(300L);
        assignTaskRequest.setUserId(1500L);
        // Throw user not found Exception
        assertThrows(TaskNotFoundException.class ,()->taskService.assignTask(assignTaskRequest));
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
    public void viewAllProjectTasksTest() {
        List<ViewTaskResponse> allProjectTasks = taskService.viewAllProjectTasks(200L);

        assertThat(allProjectTasks).isNotNull();
        assertThat(allProjectTasks.size()).isEqualTo(2);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllNonExistentProjectTasks_ThrowsExceptionTest() {
        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllProjectTasks(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllUserTasksTest() {
        List<ViewTaskResponse> allUserTasks = taskService.viewAllUserTasks(100L);

        assertThat(allUserTasks).isNotNull();
        assertThat(allUserTasks.size()).isEqualTo(3);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllNonExistentUserTasks_ThrowsExceptionTest() {
        //Throw user not found exception
        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllUserTasks(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllUserTasksInProjectTest() {
        ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest = new ViewAllUserTasksInProjectRequest();
        viewAllUserTasksInProjectRequest.setUserId(100L);
        viewAllUserTasksInProjectRequest.setProjectId(200L);
        List<ViewTaskResponse> allTasksForUserInProject = taskService.viewAllUserTasksInProject(viewAllUserTasksInProjectRequest);

        assertThat(allTasksForUserInProject).isNotNull();
        assertThat(allTasksForUserInProject.size()).isEqualTo(2);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllUserTasksInNonExistentProjectT_ThrowsExceptionTest() {
        ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest = new ViewAllUserTasksInProjectRequest();
        viewAllUserTasksInProjectRequest.setUserId(100L);
        viewAllUserTasksInProjectRequest.setProjectId(1500L);

        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllUserTasksInProject(viewAllUserTasksInProjectRequest));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteTaskTest() {
        String message = taskService.deleteTask(302L);

        assertThat(message).isNotNull();
        assertThat(message).contains("success");
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteNonExistentTask_ThrowsExceptionTest() {
        assertThrows(TaskNotFoundException.class, ()->taskService.deleteTask(1500L));
    }

}