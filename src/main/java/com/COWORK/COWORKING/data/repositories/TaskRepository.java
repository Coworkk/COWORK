package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t where t.project.projectId=:projectId")
    List<Task> findTaskByProjectId(Long projectId);
}
