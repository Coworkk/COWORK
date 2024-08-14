package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Status;
import com.COWORK.COWORKING.data.models.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    @Query("SELECT s FROM SubTask s WHERE s.task.user.userId=:userId")
    List<SubTask> findAllUserSubTasks(String userId);

    @Query("SELECT s FROM SubTask s WHERE s.task.taskId=:taskId AND s.task.user.userId=:userId")
    List<SubTask> findAllUserTasksSubTasks(String userId, Long taskId);

    @Query("SELECT s FROM SubTask s WHERE s.task.user.userId=:userId AND s.status=:status")
    List<SubTask> findAllUserSubTasksByStatus(String userId, Status status);
}
