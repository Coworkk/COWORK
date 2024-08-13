package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c from Comment c WHERE c.task.taskId=:taskId")
    List<Comment> findCommentsByTaskId(Long taskId);
}
