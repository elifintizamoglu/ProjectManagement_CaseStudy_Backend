package com.elifintizam.projectManagement.dataAccess.abstracts;

import com.elifintizam.projectManagement.entities.concretes.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByTitleAndProjectId(String name, int projectId);

    List<Task> findByProjectId(int projectId);

}
