package com.elifintizam.projectManagement.dataAccess.abstracts;

import com.elifintizam.projectManagement.entities.concretes.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t " +
            "WHERE t.project.id = :projectId " +
            "AND t.title = :title " +
            "AND t.id != :taskId")
    Optional<Task> findByTitleAndProjectIdExcludingId(int taskId, String title, int projectId);

    List<Task> findByProjectId(int projectId);

}
