package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;

import java.util.List;

public interface TaskService {

    CreateTaskResponse createTask(CreateTaskRequest createTaskRequest);

    Task findTaskById(Long taskId);

    ViewTaskResponse viewTask(Long taskId);

    List<ViewTaskResponse> viewAllProjectTasks(Long projectId);

    String deleteTask(Long taskId);
}
