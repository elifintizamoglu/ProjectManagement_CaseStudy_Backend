package com.elifintizam.projectManagement.api.controllers;

import com.elifintizam.projectManagement.business.abstracts.TaskService;
import com.elifintizam.projectManagement.business.dtos.requests.task.CreateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.requests.task.UpdateTaskRequest;
import com.elifintizam.projectManagement.business.dtos.responses.task.CreateTaskResponse;
import com.elifintizam.projectManagement.business.dtos.responses.task.GetAllTasksResponse;
import com.elifintizam.projectManagement.business.dtos.responses.task.GetTaskByIdResponse;
import com.elifintizam.projectManagement.business.dtos.responses.task.UpdateTaskResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tasks")
@AllArgsConstructor
public class TasksController {

    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTaskResponse addTask(@RequestBody CreateTaskRequest createTaskRequest) {
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
                                             @RequestBody UpdateTaskRequest updateTaskRequest) {
        return taskService.updateTaskById(id, updateTaskRequest);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTaskByIdResponse getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }
}
