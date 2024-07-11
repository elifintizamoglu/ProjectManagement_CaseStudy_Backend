package com.elifintizam.projectManagement.api.controllers;

import com.elifintizam.projectManagement.business.abstracts.ProjectService;
import com.elifintizam.projectManagement.business.dtos.requests.project.CreateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.requests.project.UpdateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.responses.project.CreateProjectResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetAllProjectsResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetProjectByIdResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.UpdateProjectResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/projects")
@AllArgsConstructor
public class ProjectsController {

    private ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProjectResponse addProject(@RequestBody CreateProjectRequest createProjectRequest) {
        return projectService.addProject(createProjectRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProjectsResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectById(@PathVariable int id) {
        projectService.deleteProjectById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateProjectResponse updateProjectById(@PathVariable int id,
                                                   @RequestBody UpdateProjectRequest updateProjectRequest) {
        return projectService.updateProjectById(id, updateProjectRequest);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProjectByIdResponse getProjectById(@PathVariable int id) {
        return projectService.getProjectById(id);
    }
}
