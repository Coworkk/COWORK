package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.SubTask;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.models.User;
import com.COWORK.COWORKING.data.repositories.SubTaskRepository;
import com.COWORK.COWORKING.data.repositories.UserRepository;
import com.COWORK.COWORKING.dtos.requests.CreateSubTaskRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserSubTasksByStatusRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllUserTaskSubTasksRequest;
import com.COWORK.COWORKING.dtos.responses.CreateSubTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewSubTaskResponse;
import com.COWORK.COWORKING.exceptions.SubTaskNotFoundException;
import com.COWORK.COWORKING.services.SubTaskService;
import com.COWORK.COWORKING.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.COWORK.COWORKING.data.models.Status.NOT_STARTED;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImplementation implements SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
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
                .startDate(createSubTaskRequest.getStartDate().truncatedTo(ChronoUnit.SECONDS))
                .dueDate(createSubTaskRequest.getDueDate().truncatedTo(ChronoUnit.SECONDS))
                .task(task)
                .build();
        subTaskRepository.save(subTask);
        CreateSubTaskResponse createSubTaskResponse = modelMapper.map(subTask, CreateSubTaskResponse.class);
        createSubTaskResponse.setMessage("Subtask created successfully");
        return createSubTaskResponse;
    }

    @Override
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
    public List<ViewSubTaskResponse> viewAllUserSubTasks(String userId) {
        User user =userRepository.getUserByUserId(userId);
        if(user==null)throw new SubTaskNotFoundException("subtask not found");
        List<SubTask> subTasks = subTaskRepository.findAllUserSubTasks(userId);
        return subTasks.stream()
                .map(subTask -> modelMapper.map(subTask, ViewSubTaskResponse.class)).toList();
    }
    @Override
    public List<ViewSubTaskResponse> viewAllUserTaskSubTasks(ViewAllUserTaskSubTasksRequest viewAllUserTaskSubTasksRequest) {
        User user =userRepository.getUserByUserId(viewAllUserTaskSubTasksRequest.getUserId());
        if(user==null)throw new SubTaskNotFoundException("subtask not found");
        List<SubTask> subTasks = subTaskRepository.findAllUserTasksSubTasks(viewAllUserTaskSubTasksRequest.getUserId(),
                viewAllUserTaskSubTasksRequest.getTaskId());
        return subTasks.stream()
                .map(subTask -> modelMapper.map(subTask, ViewSubTaskResponse.class)).toList();
    }
    @Override
    public List<ViewSubTaskResponse> viewAllUserSubTasksByStatus(ViewAllUserSubTasksByStatusRequest viewAllUserSubTasksByStatusRequest) {
        User user =userRepository.getUserByUserId(viewAllUserSubTasksByStatusRequest.getUserId());
        if(user==null)throw new SubTaskNotFoundException("subtask not found");
        List<SubTask> subTasks = subTaskRepository.findAllUserSubTasksByStatus(viewAllUserSubTasksByStatusRequest.getUserId(),
                viewAllUserSubTasksByStatusRequest.getStatus());
        return subTasks.stream()
                .map(subTask -> modelMapper.map(subTask, ViewSubTaskResponse.class)).toList();
    }
    @Override
    public String deleteSubTask(Long subTaskId) {
        SubTask subTask = findSubTaskById(subTaskId);
        subTaskRepository.delete(subTask);
        return "Subtask deleted successfully";
    }
}
