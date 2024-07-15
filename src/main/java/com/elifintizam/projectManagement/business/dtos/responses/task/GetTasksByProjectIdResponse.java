package com.elifintizam.projectManagement.business.dtos.responses.task;

import com.elifintizam.projectManagement.entities.concretes.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTasksByProjectIdResponse {

    private int id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdDate;
}
