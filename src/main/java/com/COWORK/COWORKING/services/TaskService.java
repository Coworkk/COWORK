package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.dtos.requests.AssignTaskRequest;
import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.requests.UpdateTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTasksInProjectRequest;
import com.COWORK.COWORKING.dtos.responses.AssignTaskResponse;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.UpdateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;

import java.util.List;

public interface TaskService {

    CreateTaskResponse createTask(CreateTaskRequest createTaskRequest);

    UpdateTaskResponse updateTask(UpdateTaskRequest updateTaskRequest);

    Task findTaskById(Long taskId);

    AssignTaskResponse assignTask(AssignTaskRequest assignTaskRequest);

    ViewTaskResponse viewTask(Long taskId);

    List<ViewTaskResponse> viewAllProjectTasks(Long projectId);

    List<ViewTaskResponse> viewAllUserTasks(Long userId);

    List<ViewTaskResponse> viewAllUserTasksInProject(ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest);

    String deleteTask(Long taskId);
}
