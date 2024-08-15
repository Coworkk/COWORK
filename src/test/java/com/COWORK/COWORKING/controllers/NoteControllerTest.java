package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/database/data.sql"})
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void attachNoteTest() throws Exception {
        AttachNoteRequest attachNoteRequest = new AttachNoteRequest();
        attachNoteRequest.setContent("draw the plans");
        attachNoteRequest.setProjectId(200L);
        attachNoteRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        mockMvc.perform(post("/api/v1/cowork/users/attachNote")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(attachNoteRequest))
        ).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void updateNoteTest() throws Exception {

    }

    @Test
    public void viewNoteTest() throws Exception {
        mockMvc.perform(get("/api/v1/cowork/users/viewNote/500")
                .contentType(APPLICATION_JSON)
        ).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void viewProjectNotesTest() throws Exception {
        mockMvc.perform(get("/api/v1/cowork/users/viewAllProjectNotes?projectId=200")
                .contentType(APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void viewAllUserNotesTest() throws Exception {
        mockMvc.perform(get("api/v1/note/viewAllUserNotes?userId=f62f68e8-023f-4c67-9e87-7af2a111e5eb")
                .contentType(APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void deleteNoteTest() throws Exception {
        mockMvc.perform(delete("api/v1/note/deleteNote/502")
                .contentType(APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());}
}