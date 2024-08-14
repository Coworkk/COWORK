package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.*;

import java.util.List;

public interface TaskService {

    CreateTaskResponse createTask(CreateTaskRequest createTaskRequest);

    UpdateTaskResponse updateTask(UpdateTaskRequest updateTaskRequest);

    Task findTaskById(Long taskId);

    AssignTaskResponse assignTask(AssignTaskRequest assignTaskRequest);

    ViewTaskResponse viewTask(Long taskId);

    List<ViewTaskResponse> viewAllProjectTasks(Long projectId);

    List<ViewTaskResponse> viewAllUserTasks(String userId);

    List<ViewTaskResponse> viewAllUserTasksInProject(ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest);

    List<ViewTaskResponse> viewAllUserTasksByDueDate(ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest);

    List<ViewTaskResponse> viewAllUserTasksByStatus(ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest);

    String deleteTask(Long taskId);


}
