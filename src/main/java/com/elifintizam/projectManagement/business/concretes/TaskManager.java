package com.elifintizam.projectManagement.business.concretes;

import com.elifintizam.projectManagement.business.abstracts.TaskService;
import com.elifintizam.projectManagement.business.constants.TaskMessages;
import com.elifintizam.projectManagement.business.dtos.requests.task.CreateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.requests.task.UpdateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.responses.task.*;
import com.elifintizam.projectManagement.business.rules.TaskBusinessRules;
import com.elifintizam.projectManagement.core.utilities.exceptions.types.BusinessException;
import com.elifintizam.projectManagement.core.utilities.mapping.ModelMapperService;
import com.elifintizam.projectManagement.dataAccess.abstracts.TaskRepository;
import com.elifintizam.projectManagement.entities.concretes.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskManager implements TaskService {

    private TaskRepository taskRepository;
    private ModelMapperService modelMapperService;
    private TaskBusinessRules businessRules;

    @Override
    public CreateTaskResponse addTask(CreateTaskRequest request) {

        businessRules.taskTitleCanNotBeDuplicated(0, request.getTitle(), request.getProjectId());

        Task task = modelMapperService.forRequest().map(request, Task.class);
        task.setCreatedDate(LocalDateTime.now());
        task.setId(0);
        taskRepository.save(task);

        CreateTaskResponse response = modelMapperService.forResponse().map(task, CreateTaskResponse.class);
        return response;
    }

    @Override
    public List<GetAllTasksResponse> getAllTasks() {

        List<Task> tasks = taskRepository.findAll();

        List<GetAllTasksResponse> response = tasks.stream()
                .map(task -> modelMapperService.forResponse()
                        .map(task, GetAllTasksResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteTaskById(int id) {

        businessRules.isTaskExists(id);
        taskRepository.deleteById(id);
    }

    @Override
    public UpdateTaskResponse updateTaskById(int id, UpdateTaskRequest request) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new BusinessException(TaskMessages.TaskNotFound));

        businessRules.taskTitleCanNotBeDuplicated(id, request.getTitle(), request.getProjectId());
        Task updatedTask = modelMapperService.forRequest().map(request, Task.class);

        task.setProject(updatedTask.getProject());
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setUpdatedDate(LocalDateTime.now());
        taskRepository.save(task);

        UpdateTaskResponse response = modelMapperService.forResponse().map(task, UpdateTaskResponse.class);
        return response;

    }

    @Override
    public GetTaskByIdResponse getTaskById(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new BusinessException(TaskMessages.TaskNotFound));

        GetTaskByIdResponse response = modelMapperService.forResponse().map(task, GetTaskByIdResponse.class);
        return response;
    }

    @Override
    public List<GetTasksByProjectIdResponse> getTasksByProjectId(int projectId) {

        List<Task> tasks = taskRepository.findByProjectId(projectId);

        List<GetTasksByProjectIdResponse> response = tasks.stream()
                .map(task -> modelMapperService.forResponse().map(task, GetTasksByProjectIdResponse.class)).collect(Collectors.toList());
        return response;
    }
}
