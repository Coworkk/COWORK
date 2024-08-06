package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.repositories.TaskRepository;
import com.COWORK.COWORKING.dtos.requests.CreateTaskRequest;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.COWORK.COWORKING.data.models.Status.NOT_STARTED;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService{

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    private ProjectService projectService;

    @Autowired
    @Lazy
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {
        Project project = projectService.findProjectById(createTaskRequest.getProjectId());
        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .status(NOT_STARTED)
                .startDate(createTaskRequest.getStartDate())
                .dueDate(createTaskRequest.getDueDate())
                .priority(createTaskRequest.getPriority())
                .project(project)
                .build();

        taskRepository.save(task);
        CreateTaskResponse createTaskResponse = modelMapper.map(task, CreateTaskResponse.class);
        createTaskResponse.setMessage("Task created successfully");
        return createTaskResponse;
    }

    @Override
    public ViewTaskResponse viewTask(Long taskId) {
        Task task = findTaskById(taskId);
        System.out.println(task); //remove later
        ViewTaskResponse viewTaskResponse = new ViewTaskResponse();
        viewTaskResponse.setTaskId(taskId);
        return viewTaskResponse;
    }

    @Override
    public List<ViewTaskResponse> viewAllProjectTasks(Long projectId) {
        projectService.findProjectById(projectId);
        List<Task> tasks = taskRepository.findTaskByProjectId(projectId);
        return tasks.stream()
                .map(taskItem -> modelMapper.map(taskItem, ViewTaskResponse.class)).toList();
    }

    @Override
    public String deleteTask(Long projectId) {
        return null;
    }

    private Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(()-> new TaskNotFoundException
                        (String.format("Task with %d not found", taskId)));
    }
}
