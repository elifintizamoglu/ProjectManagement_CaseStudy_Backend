package com.elifintizam.projectManagement.business.dtos.requests.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProjectRequest {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
