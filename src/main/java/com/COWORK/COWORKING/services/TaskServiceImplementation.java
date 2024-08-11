package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.models.User;
import com.COWORK.COWORKING.data.repositories.TaskRepository;
import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.AssignTaskResponse;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.UpdateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
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
                .startDate(createTaskRequest.getStartDate().truncatedTo(ChronoUnit.SECONDS))
                .dueDate(createTaskRequest.getDueDate().truncatedTo(ChronoUnit.SECONDS))
                .priority(createTaskRequest.getPriority())
                .project(project)
                .build();

        taskRepository.save(task);
        CreateTaskResponse createTaskResponse = modelMapper.map(task, CreateTaskResponse.class);
        createTaskResponse.setMessage("Task created successfully");
        System.out.println(createTaskResponse); // remove later
        return createTaskResponse;
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest updateTaskRequest) {
        Task task = findTaskById(updateTaskRequest.getTaskId());

        /*
        *     private Long taskId;
    private String title;
    private String description;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dueDate;
    private Priority priority;
        *
        *
        * */

        return null;
    }

    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(()-> new TaskNotFoundException
                        (String.format("Task with id %d not found", taskId)));
    }

    @Override
    public AssignTaskResponse assignTask(AssignTaskRequest assignTaskRequest) {
        Task task = findTaskById(assignTaskRequest.getTaskId());
        User user = null; // Validate user exists
        task.setUser(user);
        AssignTaskResponse assignTaskResponse = modelMapper.map(user, AssignTaskResponse.class);
        assignTaskResponse.setMessage("User successfully assigned to task");
        return assignTaskResponse;
    }

    @Override
    public ViewTaskResponse viewTask(Long taskId) {
        Task task = findTaskById(taskId);
        System.out.println(task); //remove later
        return modelMapper.map(task, ViewTaskResponse.class);
    }

    @Override
    public List<ViewTaskResponse> viewAllProjectTasks(Long projectId) {
        projectService.findProjectById(projectId);
        List<Task> tasks = taskRepository.findTaskByProjectId(projectId);
        return tasks.stream()
                .map(projectTask -> modelMapper.map(projectTask, ViewTaskResponse.class)).toList();
    }

    @Override
    public List<ViewTaskResponse> viewAllUserTasks(Long userId) {
        //validate User
        List<Task> tasks = taskRepository.findTaskByUserId(userId);
        return tasks.stream()
                .map(userTask -> modelMapper.map(userTask, ViewTaskResponse.class)).toList();
    }

    @Override
    public List<ViewTaskResponse> viewAllUserTasksInProject(ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest) {
        projectService.findProjectById(viewAllUserTasksInProjectRequest.getProjectId());
        //Validate user
        List<Task> tasks = taskRepository.findTaskByUserIdAndProjectId(
                viewAllUserTasksInProjectRequest.getUserId(), viewAllUserTasksInProjectRequest.getProjectId());
        return tasks.stream()
                .map(userTaskInProject -> modelMapper.map(userTaskInProject, ViewTaskResponse.class)).toList();
    }

    @Override
    public List<ViewTaskResponse> viewAllUserTasksByDueDate(ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest) {
        //Validate user
        List<Task> tasks = taskRepository.findTaskByUserIdAndDueDate(viewAllUserTasksByDueDateRequest.getUserId(),
                viewAllUserTasksByDueDateRequest.getDueDate().truncatedTo(ChronoUnit.SECONDS));
        return tasks.stream()
                .map(userTask -> modelMapper.map(userTask, ViewTaskResponse.class)).toList();
    }

    @Override
    public List<ViewTaskResponse> viewAllUserTasksByStatus(ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest) {
        // Validate User

        List<Task> tasks = taskRepository.findTaskByUserIdAndStatus(viewAllUserTasksByStatusRequest.getUserId(), viewAllUserTasksByStatusRequest.getStatus());
        return tasks.stream()
                .map(userTask -> modelMapper.map(userTask, ViewTaskResponse.class)).toList();
    }

    @Override
    public String deleteTask(Long taskId) {
        Task task = findTaskById(taskId);
        taskRepository.delete(task);
        return "Task deleted successfully";
    }

}
