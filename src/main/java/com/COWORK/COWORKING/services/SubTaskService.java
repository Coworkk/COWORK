package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;

public interface SubTaskService {

    CreateSubTaskResponse createSubTask(CreateSubTaskRequest createSubTaskRequest);

    // update subtask

    // find subtask

    ViewSubTaskResponse viewSubTask(Long subTaskId);

    //view all task subtasks

    // view all usersubtasks

    String deleteSubTask(Long subTaskId);
}
