package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.*;
import com.COWORK.COWORKING.exceptions.ProjectNotFoundException;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.COWORK.COWORKING.data.models.Priority.URGENT;
import static com.COWORK.COWORKING.data.models.Status.NOT_STARTED;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts ={"/database/data.sql"})
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void createTaskTest() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Design user interface");
        createTaskRequest.setDescription("Develop detailed wireframes and visual mockups for each app screen");
        createTaskRequest.setStartDate(now().plusDays(10));
        createTaskRequest.setDueDate(now().plusDays(20));
        createTaskRequest.setProjectId(200L);
        createTaskRequest.setPriority(URGENT);
        CreateTaskResponse createTaskResponse = taskService.createTask(createTaskRequest);

        assertNotNull(createTaskResponse);
        assertTrue(createTaskResponse.getMessage().contains("success"));
        assertThat(createTaskResponse.getProjectId()).isEqualTo(200L);
        System.out.println(createTaskResponse);
    }

    @Test
    public void createTask_WithNonExistentProject_ThrowsExceptionTest() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Integrate payment gateway");
        createTaskRequest.setDescription("Set up payment gateway to handle refunds, chargebacks, and transaction disputes");
        createTaskRequest.setStartDate(now().plusDays(10));
        createTaskRequest.setDueDate(now().plusDays(20));
        createTaskRequest.setProjectId(1500L);
        createTaskRequest.setPriority(URGENT);

        assertThrows(ProjectNotFoundException.class,()->taskService.createTask(createTaskRequest));
    }

    @Test
    public void updateTaskTest() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        UpdateTaskResponse updateTaskResponse = taskService.updateTask(updateTaskRequest);

        assertThat(updateTaskResponse).isNotNull();
    }

    @Test
    public void updateNonExistentTask_ThrowsExceptionTest() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();

        assertThrows(TaskNotFoundException.class, ()->taskService.updateTask(updateTaskRequest));
    }

    @Test
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
    public void assignNonExistentTaskToUser_ThrowsExceptionTest() {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setTaskId(1500L);
        assignTaskRequest.setUserId(100L);

        assertThrows(TaskNotFoundException.class ,()->taskService.assignTask(assignTaskRequest));
    }

    @Test
    public void assignTaskToNonExistentUser_ThrowsExceptionTest() {
        AssignTaskRequest assignTaskRequest = new AssignTaskRequest();
        assignTaskRequest.setTaskId(300L);
        assignTaskRequest.setUserId(1500L);
        // Throw user not found Exception
        assertThrows(TaskNotFoundException.class ,()->taskService.assignTask(assignTaskRequest));
    }

    @Test
    public void changeTaskStatusTest() {
//        ChangeTaskStatusResponse changeTaskStatusResponse = taskService.changeTaskStatus(300L);
//
//        assertThat(changeTaskStatusResponse).isNotNull();


    }

    @Test
    public void viewTaskTest() {
        ViewTaskResponse viewTaskResponse = taskService.viewTask(300L);

        assertThat(viewTaskResponse).isNotNull();
        assertThat(viewTaskResponse.getTaskId()).isEqualTo(300L);
        System.out.println(viewTaskResponse);
    }

    @Test
    public void viewNonExistentTask_ThrowsExceptionTest() {
        assertThrows(TaskNotFoundException.class, ()->taskService.viewTask(1500L));
    }

    @Test
    public void viewAllProjectTasksTest() {
        List<ViewTaskResponse> allProjectTasks = taskService.viewAllProjectTasks(200L);

        assertThat(allProjectTasks).isNotNull();
        assertThat(allProjectTasks.size()).isEqualTo(2);
    }

    @Test
    public void viewAllNonExistentProjectTasks_ThrowsExceptionTest() {
        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllProjectTasks(1500L));
    }

    @Test
    public void viewAllUserTasksTest() {
        List<ViewTaskResponse> allUserTasks = taskService.viewAllUserTasks(100L);

        assertThat(allUserTasks).isNotNull();
        assertThat(allUserTasks.size()).isEqualTo(3);
    }

    @Test
    public void viewAllNonExistentUserTasks_ThrowsExceptionTest() {
        //Throw user not found exception
        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllUserTasks(1500L));
    }

    @Test
    public void viewAllUserTasksInProjectTest() {
        ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest = new ViewAllUserTasksInProjectRequest();
        viewAllUserTasksInProjectRequest.setUserId(100L);
        viewAllUserTasksInProjectRequest.setProjectId(200L);
        List<ViewTaskResponse> allTasksForUserInProject = taskService.viewAllUserTasksInProject(viewAllUserTasksInProjectRequest);

        assertThat(allTasksForUserInProject).isNotNull();
        assertThat(allTasksForUserInProject.size()).isEqualTo(2);
    }

    @Test
    public void viewAllUserTasksInNonExistentProject_ThrowsExceptionTest() {
        ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest = new ViewAllUserTasksInProjectRequest();
        viewAllUserTasksInProjectRequest.setUserId(100L);
        viewAllUserTasksInProjectRequest.setProjectId(1500L);

        assertThrows(ProjectNotFoundException.class, ()->taskService.viewAllUserTasksInProject(viewAllUserTasksInProjectRequest));
    }

    @Test
    public void viewAllUserTasksByDueDateTest() {
        ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest = new ViewAllUserTasksByDueDateRequest();
        viewAllUserTasksByDueDateRequest.setUserId(100L);
        viewAllUserTasksByDueDateRequest.setDueDate(LocalDateTime.parse("2024-09-09 09:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        List<ViewTaskResponse> userProjectTasksByDueDate = taskService.viewAllUserTasksByDueDate(viewAllUserTasksByDueDateRequest);

        assertThat(userProjectTasksByDueDate).isNotNull();
        assertThat(userProjectTasksByDueDate.size()).isEqualTo(1);
    }

    @Test
    public void viewAllUserTasksByStatus() {
        ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest = new ViewAllUserTasksByStatusRequest();
        viewAllUserTasksByStatusRequest.setStatus(NOT_STARTED);
        viewAllUserTasksByStatusRequest.setUserId(100L);

        List<ViewTaskResponse> userTasksByStatus = taskService.viewAllUserTasksByStatus(viewAllUserTasksByStatusRequest);

        assertThat(userTasksByStatus).isNotNull();
        assertThat(userTasksByStatus.size()).isEqualTo(1);
    }

    @Test
    public void deleteTaskTest() {
        String message = taskService.deleteTask(302L);

        assertThat(message).isNotNull();
        assertThat(message).contains("success");
    }

    @Test
    public void deleteNonExistentTask_ThrowsExceptionTest() {
        assertThrows(TaskNotFoundException.class, ()->taskService.deleteTask(1500L));
    }

}