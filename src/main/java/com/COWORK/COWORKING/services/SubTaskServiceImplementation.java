package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Status;
import com.COWORK.COWORKING.data.models.SubTask;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.repositories.SubTaskRepository;
import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;
import com.COWORK.COWORKING.exceptions.SubTaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static com.COWORK.COWORKING.data.models.Status.NOT_STARTED;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImplementation implements SubTaskService{

    private final SubTaskRepository subTaskRepository;
    private final ModelMapper modelMapper;
    private TaskService taskService;

    @Autowired
    @Lazy
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public CreateSubTaskResponse createSubTask(CreateSubTaskRequest createSubTaskRequest) {
        Task task = taskService.findTaskById(createSubTaskRequest.getTaskId());
        SubTask subTask = SubTask.builder()
                .title(createSubTaskRequest.getTitle())
                .description(createSubTaskRequest.getDescription())
                .status(NOT_STARTED)
                .startDate(createSubTaskRequest.getStartDate())
                .dueDate(createSubTaskRequest.getDueDate())
                .task(task)
                .build();

        subTaskRepository.save(subTask);
        CreateSubTaskResponse createSubTaskResponse = modelMapper.map(subTask, CreateSubTaskResponse.class);
        createSubTaskResponse.setMessage("Subtask created successfully");
        return createSubTaskResponse;
    }

    public SubTask findSubTaskById(Long subTaskId) {
        return subTaskRepository.findById(subTaskId)
                .orElseThrow(()-> new SubTaskNotFoundException
                        (String.format("Subtask with %d not found", subTaskId)));
    }

    @Override
    public ViewSubTaskResponse viewSubTask(Long subTaskId) {
        SubTask subTask = findSubTaskById(subTaskId);
        return modelMapper.map(subTask, ViewSubTaskResponse.class);
    }

    @Override
    public String deleteSubTask(Long subTaskId) {
        SubTask subTask = findSubTaskById(subTaskId);
        subTaskRepository.delete(subTask);
        return "Subtask deleted successfully";
    }
}
