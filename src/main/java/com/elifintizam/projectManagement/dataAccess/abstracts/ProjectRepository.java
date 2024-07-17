package com.elifintizam.projectManagement.dataAccess.abstracts;

import com.elifintizam.projectManagement.entities.concretes.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT p FROM Project p " +
            "WHERE p.id != :id " +
            "AND p.name = :name")
    Optional<Project> findByNameExcludingId(int id, String name);

    @Query("SELECT p FROM Project p " +
            "ORDER BY p.name ASC")
    List<Project> findAllOrderByNameAsc();
}
