package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AddCommentRequest;
import com.COWORK.COWORKING.dtos.requests.EditCommentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts ={"/database/data.sql"})
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }
    @Test
    public void addCommentTest() throws Exception {
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setComment("comment");
        addCommentRequest.setTaskId(300L);
        addCommentRequest.setUserId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        mockMvc.perform(post("/api/v1/cowork/users/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(addCommentRequest))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andDo(print());
    }
    @Test
    public void editCommentTest() throws Exception {
        EditCommentRequest editcommentRequest = new EditCommentRequest();
        editcommentRequest.setCommentId(600L);
        editcommentRequest.setComment("fdsf");
        mockMvc.perform(patch("/api/v1/cowork/users/editComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(editcommentRequest))
        ).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void viewAllTaskCommentsTest() throws Exception {
        mockMvc.perform(get("/api/v1/cowork/users/viewAllTaskComments?taskId=300")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void deleteComment() throws Exception {
        mockMvc.perform(delete("/api/v1/cowork/users/deleteComment/600")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }
}