package com.elifintizam.projectManagement.business.concretes;

import com.elifintizam.projectManagement.business.abstracts.ProjectService;
import com.elifintizam.projectManagement.business.constants.ProjectMessages;
import com.elifintizam.projectManagement.business.dtos.requests.project.CreateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.requests.project.UpdateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.responses.project.CreateProjectResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetAllProjectsResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetProjectByIdResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.UpdateProjectResponse;
import com.elifintizam.projectManagement.business.rules.ProjectBusinessRules;
import com.elifintizam.projectManagement.core.utilities.exceptions.types.BusinessException;
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
    private ProjectBusinessRules businessRules;

    @Override
    public CreateProjectResponse addProject(CreateProjectRequest request) {

        businessRules.projectNameCanNotBeDuplicated(0, request.getName());
        businessRules.isDatesAppropriate(request.getStartDate(), request.getEndDate());

        Project project = modelMapperService.forRequest().map(request, Project.class);
        project.setCreatedDate(LocalDateTime.now());
        projectRepository.save(project);

        CreateProjectResponse response = modelMapperService.forResponse().map(project, CreateProjectResponse.class);
        return response;
    }

    @Override
    public List<GetAllProjectsResponse> getAllProjects() {

        List<Project> projects = projectRepository.findAllOrderByNameAsc();

        List<GetAllProjectsResponse> response = projects.stream()
                .map(project -> modelMapperService.forResponse()
                        .map(project, GetAllProjectsResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteProjectById(int id) {

        businessRules.isProjectExists(id);
        projectRepository.deleteById(id);
    }

    @Override
    public UpdateProjectResponse updateProjectById(int id, UpdateProjectRequest request) {

        Project project = projectRepository.findById(id).orElseThrow(() -> new BusinessException(ProjectMessages.ProjectNotFound));

        businessRules.projectNameCanNotBeDuplicated(id, request.getName());
        businessRules.isDatesAppropriate(request.getStartDate(), request.getEndDate());

        Project updatedProject = modelMapperService.forRequest().map(request, Project.class);

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

        Project project = projectRepository.findById(id).orElseThrow(() -> new BusinessException(ProjectMessages.ProjectNotFound));

        GetProjectByIdResponse response = modelMapperService.forResponse().map(project, GetProjectByIdResponse.class);
        return response;
    }
}
