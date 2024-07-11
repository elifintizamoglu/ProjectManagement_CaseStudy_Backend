package com.elifintizam.projectManagement.business.rules;

import com.elifintizam.projectManagement.business.constants.TaskMessages;
import com.elifintizam.projectManagement.core.utilities.exceptions.types.BusinessException;
import com.elifintizam.projectManagement.dataAccess.abstracts.TaskRepository;
import com.elifintizam.projectManagement.entities.concretes.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskBusinessRules {

    TaskRepository taskRepository;

    public void taskTitleCanNotBeDuplicated(String title, int projectId) {

        String trimmedTitle = title.trim();
        Optional<Task> task = taskRepository.findByTitleAndProjectId(trimmedTitle, projectId);

        if (task.isPresent()) {
            throw new BusinessException(TaskMessages.TaskTitleAlreadyExists);
        }
    }

    public void isTaskExists(int taskId) {

        boolean isExists = taskRepository.existsById(taskId);
        if (!isExists) {
            throw new BusinessException(TaskMessages.TaskNotFound);
        }
    }
}
