package com.elifintizam.projectManagement.business.dtos.requests.task;

import com.elifintizam.projectManagement.entities.concretes.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTaskRequest {

    private int projectId;
    private String title;
    private String description;
    private Status status;
}
