package com.elifintizam.projectManagement.business.concretes;

import com.elifintizam.projectManagement.business.abstracts.ProjectService;
import com.elifintizam.projectManagement.business.dtos.requests.project.CreateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.requests.project.UpdateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.responses.project.CreateProjectResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetAllProjectsResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetProjectByIdResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.UpdateProjectResponse;
import com.elifintizam.projectManagement.core.utilities.mapping.ModelMapperService;
import com.elifintizam.projectManagement.dataAccess.abstracts.ProjectRepository;
import com.elifintizam.projectManagement.entities.concretes.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectManager implements ProjectService {

    private ProjectRepository projectRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreateProjectResponse addProject(CreateProjectRequest createProjectRequest) {

        Project project = modelMapperService.forRequest().map(createProjectRequest, Project.class);
        project.setCreatedDate(LocalDateTime.now());
        projectRepository.save(project);

        CreateProjectResponse response = modelMapperService.forResponse().map(project, CreateProjectResponse.class);
        return response;
    }

    @Override
    public List<GetAllProjectsResponse> getAllProjects() {

        List<Project> projects = projectRepository.findAll();

        List<GetAllProjectsResponse> response = projects.stream()
                .map(project -> modelMapperService.forResponse()
                        .map(project, GetAllProjectsResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteProjectById(int id) {

        projectRepository.deleteById(id);
    }

    @Override
    public UpdateProjectResponse updateProjectById(int id, UpdateProjectRequest updateProjectRequest) {

        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("ProjectNotFound"));

        Project updatedProject = modelMapperService.forRequest().map(updateProjectRequest, Project.class);

        project.setName(updatedProject.getName());
        project.setStartDate(updatedProject.getStartDate());
        project.setEndDate(updatedProject.getEndDate());
        project.setUpdatedDate(LocalDateTime.now());
        projectRepository.save(project);

        UpdateProjectResponse response = modelMapperService.forResponse().map(project, UpdateProjectResponse.class);
        return response;
    }

    @Override
    public GetProjectByIdResponse getProjectById(int id) {

        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("ProjectNotFound"));

        GetProjectByIdResponse response = modelMapperService.forResponse().map(project, GetProjectByIdResponse.class);
        return response;
    }
}
