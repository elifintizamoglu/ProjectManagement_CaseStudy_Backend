package com.elifintizam.projectManagement.business.rules;


import com.elifintizam.projectManagement.business.constants.ProjectMessages;
import com.elifintizam.projectManagement.core.utilities.exceptions.types.BusinessException;
import com.elifintizam.projectManagement.dataAccess.abstracts.ProjectRepository;
import com.elifintizam.projectManagement.entities.concretes.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectBusinessRules {

    ProjectRepository projectRepository;

    public void projectNameCanNotBeDuplicated(String name) {

        String trimmedName = name.trim();
        Optional<Project> project = projectRepository.findByName(trimmedName);

        if (project.isPresent()) {
            throw new BusinessException(ProjectMessages.ProjectNameAlreadyExists);
        }
    }

    public void isDatesAppropriate(LocalDate startDate, LocalDate endDate) {

        if(!(startDate.isBefore(endDate) || startDate.isEqual(endDate))){
            throw new BusinessException(ProjectMessages.DatesAreNotAppropriate);
        }

    }

    public void isProjectExists(int projectId) {

        boolean isExists = projectRepository.existsById(projectId);
        if (!isExists) {
            throw new BusinessException(ProjectMessages.ProjectNotFound);
        }
    }
}
