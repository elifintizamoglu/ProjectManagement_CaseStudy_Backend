package com.elifintizam.projectManagement.business.abstracts;

import com.elifintizam.projectManagement.business.dtos.requests.project.CreateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.requests.project.UpdateProjectRequest;
import com.elifintizam.projectManagement.business.dtos.responses.project.CreateProjectResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetAllProjectsResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.GetProjectByIdResponse;
import com.elifintizam.projectManagement.business.dtos.responses.project.UpdateProjectResponse;

import java.util.List;

public interface ProjectService {

    CreateProjectResponse addProject(CreateProjectRequest createProjectRequest);

    List<GetAllProjectsResponse> getAllProjects();

    void deleteProjectById(int id);

    UpdateProjectResponse updateProjectById(int id, UpdateProjectRequest updateProjectRequest);

    GetProjectByIdResponse getProjectById(int id);


}
