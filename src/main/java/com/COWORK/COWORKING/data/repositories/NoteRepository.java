package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
