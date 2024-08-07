package com.elifintizam.projectManagement.business.abstracts;

import com.elifintizam.projectManagement.business.dtos.requests.task.CreateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.requests.task.UpdateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.responses.task.*;

import java.util.List;

public interface TaskService {

    CreateTaskResponse addTask(CreateTaskRequest createTaskRequest);

    List<GetAllTasksResponse> getAllTasks();

    void deleteTaskById(int id);

    UpdateTaskResponse updateTaskById(int id, UpdateTaskRequest updateTaskRequest);

    GetTaskByIdResponse getTaskById(int id);

    List<GetTasksByProjectIdResponse> getTasksByProjectId(int projectId);
}
