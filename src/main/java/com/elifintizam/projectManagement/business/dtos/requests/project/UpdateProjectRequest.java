package com.elifintizam.projectManagement.business.dtos.requests.project;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProjectRequest {

    @NotBlank(message = "Project name can not be empty.")
    @Size(min = 2, max = 100, message = "Project name must be between 2 and 100 characters.")
    private String name;

    @NotNull(message = "Start date can not be empty.")
    @FutureOrPresent(message = "Start date must be today or in the future.")
    private LocalDate startDate;

    @NotNull(message = "End date can not be empty.")
    @FutureOrPresent(message = "End date must be today or in the future.")
    private LocalDate endDate;
}
