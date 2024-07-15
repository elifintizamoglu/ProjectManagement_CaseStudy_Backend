package com.elifintizam.projectManagement.api.controllers;

import com.elifintizam.projectManagement.business.abstracts.TaskService;
import com.elifintizam.projectManagement.business.dtos.requests.task.CreateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.requests.task.UpdateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.responses.task.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TasksController {

    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTaskResponse addTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        return taskService.addTask(createTaskRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTasksResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskById(@PathVariable int id) {
        taskService.deleteTaskById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateTaskResponse updateTaskById(@PathVariable int id,
                                             @RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        return taskService.updateTaskById(id, updateTaskRequest);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTaskByIdResponse getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("getByProjectId/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetTasksByProjectIdResponse> getTasksByProjectId(@PathVariable int projectId) {
        return taskService.getTasksByProjectId(projectId);
    }
}
