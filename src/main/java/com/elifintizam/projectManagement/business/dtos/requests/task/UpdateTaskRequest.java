package com.elifintizam.projectManagement.business.dtos.requests.task;

import com.elifintizam.projectManagement.entities.concretes.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTaskRequest {

    @NotBlank(message = "Project id can not be empty.")
    @Positive(message = "Project id must be bigger than 0.")
    private int projectId;

    @NotBlank(message = "Task title can not be empty.")
    @Size(min = 2, max = 100, message = "Task title must be between 2 and 100 characters.")
    private String title;

    @Size(min = 2, max = 500, message = "Task description must be between 2 and 500 characters.")
    private String description;

    @NotBlank(message = "Status can not be empty.")
    private Status status;
}
