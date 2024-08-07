package com.elifintizam.projectManagement.business.dtos.responses.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetProjectByIdResponse {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
