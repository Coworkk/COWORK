package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.models.User;
import com.COWORK.COWORKING.data.repositories.TaskRepository;
import com.COWORK.COWORKING.data.repositories.UserRepository;
import com.COWORK.COWORKING.dtos.requests.*;
import com.COWORK.COWORKING.dtos.responses.AssignTaskResponse;
import com.COWORK.COWORKING.dtos.responses.CreateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.UpdateTaskResponse;
import com.COWORK.COWORKING.dtos.responses.ViewTaskResponse;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import com.COWORK.COWORKING.services.ProjectService;
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
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private ProjectService projectService;
    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {
        Project project = projectService.findProjectById(createTaskRequest.getProjectId());
        if(project==null)throw new RuntimeException("something went wrong");
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
        return modelMapper.map(task, CreateTaskResponse.class);
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest updateTaskRequest) {
        Task task = findTaskById(updateTaskRequest.getTaskId());
        task.setTitle(updateTaskRequest.getTitle());
        task.setDescription(updateTaskRequest.getDescription());
        task.setStartDate(updateTaskRequest.getStartDate().truncatedTo(ChronoUnit.MINUTES));
        task.setDueDate(updateTaskRequest.getDueDate().truncatedTo(ChronoUnit.MINUTES));
        task.setPriority(updateTaskRequest.getPriority());
        task.setStatus(updateTaskRequest.getStatus());
        task=taskRepository.save(task);
        return UpdateTaskResponse.builder().priority(task.getPriority()).
                taskId(task.getTaskId()).description(task.getDescription()).dueDate(task.getDueDate())
                .title(task.getTitle()).status(task.getStatus()).title(task.getTitle()).startDate(task.getStartDate())
                .build();
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
        User user = userRepository.getUserByUserId(assignTaskRequest.getUserId());
        if(user==null)throw new RuntimeException("something went wrong");
        task.setUser(user);
        AssignTaskResponse assignTaskResponse = modelMapper.map(assignTaskRequest, AssignTaskResponse.class);
        assignTaskResponse.setMessage("task successfully assigned to "+user.getFirstName());
        return assignTaskResponse;
    }



    @Override
    public ViewTaskResponse viewTask(Long taskId) {
        Task task = findTaskById(taskId);
        if(task==null)throw  new TaskNotFoundException("something went wrong");
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
    public List<ViewTaskResponse> viewAllUserTasks(String userId) {
        User user =userRepository.getUserByUserId(userId);
        if(user==null)throw new RuntimeException("something went wrong");
        List<Task> tasks = taskRepository.findTaskByUserId(userId);
        return tasks.stream()
                .map(userTask -> modelMapper.map(userTask, ViewTaskResponse.class)).toList();
    }
    @Override
    public List<ViewTaskResponse> viewAllUserTasksInProject(ViewAllUserTasksInProjectRequest viewAllUserTasksInProjectRequest) {
        projectService.findProjectById(viewAllUserTasksInProjectRequest.getProjectId());
        User user = userRepository.getUserByUserId(viewAllUserTasksInProjectRequest.getUserId());
        if(user==null)throw new RuntimeException("something went wrong");
        List<Task> tasks = taskRepository.findTaskByUserIdAndProjectId(
                viewAllUserTasksInProjectRequest.getUserId(), viewAllUserTasksInProjectRequest.getProjectId());
        return tasks.stream()
                .map(userTaskInProject -> modelMapper.map(userTaskInProject, ViewTaskResponse.class)).toList();
    }

    @Override
    public List<ViewTaskResponse> viewAllUserTasksByDueDate(ViewAllUserTasksByDueDateRequest viewAllUserTasksByDueDateRequest) {
        User user =userRepository.getUserByUserId(viewAllUserTasksByDueDateRequest.getUserId());
        if(user == null) throw new RuntimeException("user not found");
        List<Task> tasks = taskRepository.findTaskByUserIdAndDueDate(viewAllUserTasksByDueDateRequest.getUserId(),viewAllUserTasksByDueDateRequest.getDueDate().truncatedTo(ChronoUnit.MINUTES));
        return tasks.stream()
                .map(userTask -> modelMapper.map(userTask, ViewTaskResponse.class)).toList();
    }

    @Override
    public List<ViewTaskResponse> viewAllUserTasksByStatus(ViewAllUserTasksByStatusRequest viewAllUserTasksByStatusRequest) {
        User user =userRepository.getUserByUserId(viewAllUserTasksByStatusRequest.getUserId());
        if(user == null) throw new RuntimeException("user not found");
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
