package com.elifintizam.projectManagement.dataAccess.abstracts;

import com.elifintizam.projectManagement.entities.concretes.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
