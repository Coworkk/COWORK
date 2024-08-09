package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.project.projectId=:projectId")
    List<Note> findNoteByProjectId(Long projectId);

    @Query("SELECT n FROM Note n WHERE n.user.userId=:userId")
    List<Note> findNoteByUserId(Long userId);
}
