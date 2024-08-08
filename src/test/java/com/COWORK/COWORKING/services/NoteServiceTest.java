package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllProjectNotesRequest;
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
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void attachNoteTest() {
        AttachNoteRequest attachNoteRequest = new AttachNoteRequest();
        attachNoteRequest.setDescription("");
        attachNoteRequest.setProjectId(200L);
        //attachNoteRequest.setUserId(""); make sure you set user id
        AttachNoteResponse attachNoteResponse = noteService.attachNote(attachNoteRequest);

        assertThat(attachNoteResponse).isNotNull();
        assertTrue(attachNoteResponse.getMessage().contains("success"));
        assertThat(attachNoteResponse.getProjectId()).isEqualTo(200L);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void attachNoteToNonExistentProject_ThrowsExceptionTest() {
        AttachNoteRequest attachNoteRequest = new AttachNoteRequest();
        attachNoteRequest.setDescription("");
        attachNoteRequest.setProjectId(1500L);
        //attachNoteRequest.setUserId(""); make sure you set user id

        assertThrows(ProjectNotFoundException.class, ()->noteService.attachNote(attachNoteRequest));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewNoteTest() {
        ViewNoteResponse viewNoteResponse = noteService.viewNote(500L);

        assertThat(viewNoteResponse).isNotNull();
        //find what to map
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewNonExistentNote_ThrowsExceptionTest() {
        assertThrows(NoteNotFoundException.class, ()->noteService.viewNote(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewAllProjectNotesTest() {
        List<ViewNoteResponse> projectNotes = noteService.viewAllProjectNotes(200L);

        assertThat(projectNotes).isNotNull();
        assertThat(projectNotes.size()).isEqualTo(2);
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void viewNonExistentProjectNotes_ThrowsExceptionTest() {
        assertThrows(ProjectNotFoundException.class,()-> noteService.viewAllProjectNotes(1500L));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteNoteTest() {
        String message = noteService.deleteNote(502L);

        assertThat(message).isNotNull();
        assertTrue(message.contains("success"));
    }

    @Test
    @Sql(scripts = {"/database/data.sql"})
    public void deleteNonExistentNote_ThrowsExceptionTest() {
        assertThrows(NoteNotFoundException.class, ()->noteService.deleteNote(1500L));
    }


}