package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.SubTask;
import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserSubTasksByStatusRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTaskSubTasksRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;

import java.util.List;

public interface SubTaskService {

    CreateSubTaskResponse createSubTask(CreateSubTaskRequest createSubTaskRequest);

    SubTask findSubTaskById(Long subTaskId);

    // update subtask

    // UPDATE STATUS

    ViewSubTaskResponse viewSubTask(Long subTaskId);

    //---view all user task subtasks

    //view all user task subtask by status

    // ----view all usersubtasks

    //---view subtaskby status

    List<ViewSubTaskResponse> viewAllUserSubTasks(String userId);

    List<ViewSubTaskResponse> viewAllUserTaskSubTasks(ViewAllUserTaskSubTasksRequest viewAllUserTaskSubTasksRequest);

    List<ViewSubTaskResponse> viewAllUserSubTasksByStatus(ViewAllUserSubTasksByStatusRequest viewAllUserSubTasksByStatusRequest);

    String deleteSubTask(Long subTaskId);


}
