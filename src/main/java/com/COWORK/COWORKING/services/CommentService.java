package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Comment;
import com.COWORK.COWORKING.dtos.responses.DeleteCommentResponse;
import com.COWORK.COWORKING.dtos.requests.AddCommentRequest;
import com.COWORK.COWORKING.dtos.requests.EditCommentRequest;
import com.COWORK.COWORKING.dtos.responses.AddCommentResponse;
import com.COWORK.COWORKING.dtos.responses.EditCommentResponse;
import com.COWORK.COWORKING.dtos.responses.ViewCommentResponse;

import java.util.List;

public interface CommentService {
    AddCommentResponse addComment(AddCommentRequest addCommentRequest);
    EditCommentResponse editComment(EditCommentRequest editcommentRequest);
    Comment findCommentById(Long commentId);
    List<ViewCommentResponse> viewAllTaskComments(Long taskId);
    DeleteCommentResponse deleteComment(Long commentId);
}
