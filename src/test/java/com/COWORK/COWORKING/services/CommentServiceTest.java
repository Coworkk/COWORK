package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AddCommentRequest;
import com.COWORK.COWORKING.dtos.requests.EditCommentRequest;
import com.COWORK.COWORKING.dtos.responses.AddCommentResponse;
import com.COWORK.COWORKING.dtos.responses.EditCommentResponse;
import com.COWORK.COWORKING.dtos.responses.ViewCommentResponse;
import com.COWORK.COWORKING.exceptions.CommentNotFoundException;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/database/data.sql"})
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void addCommentTest() {
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setComment("fdfd");
        addCommentRequest.setTaskId(300L);
        addCommentRequest.setUserId(100L);
        AddCommentResponse addCommentResponse = commentService.addComment(addCommentRequest);

        assertThat(addCommentResponse).isNotNull();
        assertTrue(addCommentResponse.getMessage().contains("success"));
        //assertThat(addCommentResponse.getUserId()).isEqualTo(100L);

    }

    @Test
    public void addCommentToNonExistentTask_ThrowsExceptionTest() {
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setComment("fdfd");
        addCommentRequest.setTaskId(1500L);
        addCommentRequest.setUserId(100L);

        assertThrows(TaskNotFoundException.class, ()->commentService.addComment(addCommentRequest));
    }

    @Test
    public void editCommentTest() {
        EditCommentRequest editcommentRequest = new EditCommentRequest();
        editcommentRequest.setCommentId(600L);
        editcommentRequest.setComment("fdsf");
        EditCommentResponse editCommentResponse = commentService.editComment(editcommentRequest);

        assertThat(editCommentResponse).isNotNull();
        assertThat(editCommentResponse.getCommentId()).isEqualTo(600L);
        assertTrue(editCommentResponse.getMessage().contains("success"));
    }

    @Test
    public void editNonExistentCommentTest() {
        EditCommentRequest editcommentRequest = new EditCommentRequest();
        editcommentRequest.setCommentId(150L);
        editcommentRequest.setComment("fdsf");

        assertThrows(CommentNotFoundException.class, ()->commentService.editComment(editcommentRequest));
    }

    @Test
    public void viewAllTaskCommentsTest() {
        List<ViewCommentResponse> taskComments = commentService.viewAllTaskComments(300L);

        assertThat(taskComments).isNotNull();
        assertThat(taskComments.size()).isEqualTo(1);
    }

    @Test
    public void viewNonExistentTaskComments_ThrowsExceptionTest() {
        assertThrows(TaskNotFoundException.class, ()-> commentService.viewAllTaskComments(1500L));
    }

}