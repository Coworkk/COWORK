package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.Comment;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.repositories.CommentRepository;
import com.COWORK.COWORKING.dtos.requests.AddCommentRequest;
import com.COWORK.COWORKING.dtos.requests.EditCommentRequest;
import com.COWORK.COWORKING.dtos.responses.AddCommentResponse;
import com.COWORK.COWORKING.dtos.responses.EditCommentResponse;
import com.COWORK.COWORKING.dtos.responses.ViewCommentResponse;
import com.COWORK.COWORKING.exceptions.CommentNotFoundException;
import com.COWORK.COWORKING.services.CommentService;
import com.COWORK.COWORKING.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation  implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private TaskService taskService;

    @Autowired
    @Lazy
    private void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public AddCommentResponse addComment(AddCommentRequest addCommentRequest) {
        //validate user object
        Task task = taskService.findTaskById(addCommentRequest.getTaskId());
        Comment comment = Comment.builder()
                .comment(addCommentRequest.getComment())
                .task(task)
                .build();
        commentRepository.save(comment);
        AddCommentResponse addCommentResponse = modelMapper.map(comment, AddCommentResponse.class);
        addCommentResponse.setMessage("Comment added successfully");
        System.out.println(addCommentResponse);
        return addCommentResponse;
    }

    @Override
    public EditCommentResponse editComment(EditCommentRequest editcommentRequest) {
        // validate user
        Comment comment = findCommentById(editcommentRequest.getCommentId());
        System.out.println(comment);
        if (editcommentRequest.getComment() != null) {
            comment.setComment(editcommentRequest.getComment());
        }
        commentRepository.save(comment);
        EditCommentResponse editCommentResponse = modelMapper.map(comment, EditCommentResponse.class);
        editCommentResponse.setMessage("Comment edited successfully");
        System.out.println(comment);
        return editCommentResponse;
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException
                        (String.format("Comment with id %d not found", commentId)));
    }

    @Override
    public List<ViewCommentResponse> viewAllTaskComments(Long taskId) {
        taskService.findTaskById(taskId);
        List<Comment> taskComments = commentRepository.findCommentsByTaskId(taskId);
        return taskComments.stream()
                .map(comment -> modelMapper.map(comment, ViewCommentResponse.class)).toList();
    }
}
