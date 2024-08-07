package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;

public interface SubTaskService {

    CreateSubTaskResponse createSubTask(CreateSubTaskRequest createSubTaskRequest);

    ViewSubTaskResponse viewSubTask(Long subTaskId);

    String deleteSubTask(Long subTaskId);
}
