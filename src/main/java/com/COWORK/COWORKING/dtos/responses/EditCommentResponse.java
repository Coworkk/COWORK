package com.COWORK.COWORKING.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditCommentResponse {
    private Long commentId;
    private Long taskId;
    private String content;
    private String message;
}
