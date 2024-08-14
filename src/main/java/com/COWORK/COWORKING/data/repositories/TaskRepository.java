package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Status;
import com.COWORK.COWORKING.data.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t where t.project.projectId=:projectId")
    List<Task> findTaskByProjectId(Long projectId);

    @Query("SELECT t from Task t WHERE t.user.userId=:userId")
    List<Task> findTaskByUserId(String userId);

    @Query("SELECT t from Task t WHERE t.user.userId=:userId AND t.project.projectId=:projectId")
    List<Task> findTaskByUserIdAndProjectId(String userId, Long projectId);

    @Query("SELECT t FROM Task t WHERE t.user.userId=:userId AND t.dueDate=:dueDate")
    List<Task> findTaskByUserIdAndDueDate(String userId, LocalDateTime dueDate);

    @Query("SELECT t FROM Task t WHERE t.user.userId=:userId AND t.status=:status")
    List<Task> findTaskByUserIdAndStatus(String userId, Status status);
}
