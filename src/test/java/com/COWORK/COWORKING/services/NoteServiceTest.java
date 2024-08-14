package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.responses.AttachNoteResponse;
import com.COWORK.COWORKING.dtos.responses.ViewNoteResponse;
import com.COWORK.COWORKING.exceptions.NoteNotFoundException;
import com.COWORK.COWORKING.exceptions.ProjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = {"/database/data.sql"})
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Test
    public void attachNoteTest() {
        AttachNoteRequest attachNoteRequest = new AttachNoteRequest();
        attachNoteRequest.setContent("");
        attachNoteRequest.setProjectId(200L);
        attachNoteRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        AttachNoteResponse attachNoteResponse = noteService.attachNote(attachNoteRequest);
        assertThat(attachNoteResponse).isNotNull();
        assertTrue(attachNoteResponse.getMessage().contains("success"));
        assertThat(attachNoteResponse.getProjectId()).isEqualTo(200L);
    }

    @Test
    public void attachNoteToNonExistentProject_ThrowsExceptionTest() {
        AttachNoteRequest attachNoteRequest = new AttachNoteRequest();
        attachNoteRequest.setContent("");
        attachNoteRequest.setProjectId(1500L);
        attachNoteRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        assertThrows(ProjectNotFoundException.class, ()->noteService.attachNote(attachNoteRequest));
    }

    @Test
    public void viewNoteTest() {
        ViewNoteResponse viewNoteResponse = noteService.viewNote(500L);
        //find what to map
        assertThat(viewNoteResponse).isNotNull();
    }

    @Test
    public void viewNonExistentNote_ThrowsExceptionTest() {
        assertThrows(NoteNotFoundException.class, ()->noteService.viewNote(1500L));
    }

    @Test
    public void viewAllProjectNotesTest() {
        List<ViewNoteResponse> projectNotes = noteService.viewAllProjectNotes(200L);
        assertThat(projectNotes).isNotNull();
        assertThat(projectNotes.size()).isEqualTo(2);
    }

    @Test
    public void viewNonExistentProjectNotes_ThrowsExceptionTest() {
        assertThrows(ProjectNotFoundException.class,()-> noteService.viewAllProjectNotes(1500L));
    }

    @Test
    public void viewAllUserNotesTest() {
        List<ViewNoteResponse> userNotes = noteService.viewAllUserNotes("f62f68e8-023f-4c67-9e87-7af2a111e5eb");

        assertThat(userNotes).isNotNull();
        assertThat(userNotes.size()).isEqualTo(3);
    }

    @Test
    public void viewNonExistentUserNotes_ThrowsExceptionTest() {
        assertThrows(RuntimeException.class, ()-> noteService.viewAllUserNotes(""));
    }


    @Test
    public void deleteNoteTest() {
        String message = noteService.deleteNote(502L);

        assertThat(message).isNotNull();
        assertTrue(message.contains("success"));
    }

    @Test
    public void deleteNonExistentNote_ThrowsExceptionTest() {
        assertThrows(NoteNotFoundException.class, ()->noteService.deleteNote(1500L));
    }


}