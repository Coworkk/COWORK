package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.Comment;
import com.COWORK.COWORKING.data.models.Task;
import com.COWORK.COWORKING.data.models.User;
import com.COWORK.COWORKING.data.repositories.CommentRepository;
import com.COWORK.COWORKING.data.repositories.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    private void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public AddCommentResponse addComment(AddCommentRequest addCommentRequest) {
        User user = userRepository.getUserByUserId(addCommentRequest.getUserId());
        if(user==null)throw new RuntimeException("user not found");
        Task task = taskService.findTaskById(addCommentRequest.getTaskId());
        Comment comment = Comment.builder()
                .comment(addCommentRequest.getComment())
                .task(task)
                .commenter(user)
                .build();
        comment=commentRepository.save(comment);
        AddCommentResponse addCommentResponse = modelMapper.map(comment, AddCommentResponse.class);
       // addCommentResponse.setCommenterId(user.getUserId());
        addCommentResponse.setMessage("Comment added successfully");
        System.out.println(addCommentResponse);
        return addCommentResponse;
    }

    @Override
    public EditCommentResponse editComment(EditCommentRequest editcommentRequest) {
        Comment comment = findCommentById(editcommentRequest.getCommentId());
        if (editcommentRequest.getComment() != null) {
            comment.setComment(editcommentRequest.getComment());
        }
        comment =commentRepository.save(comment);
        EditCommentResponse editCommentResponse = modelMapper.map(comment, EditCommentResponse.class);
        editCommentResponse.setMessage("Comment edited successfully");
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
