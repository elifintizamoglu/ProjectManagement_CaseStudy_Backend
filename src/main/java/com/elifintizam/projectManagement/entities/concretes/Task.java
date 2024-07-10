package com.elifintizam.projectManagement.entities.concretes;

import com.elifintizam.projectManagement.core.entities.BaseEntity;
import com.elifintizam.projectManagement.entities.concretes.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
