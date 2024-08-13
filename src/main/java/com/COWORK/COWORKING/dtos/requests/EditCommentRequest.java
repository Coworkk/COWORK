package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditCommentRequest {
    private Long commentId;
    private String comment;
}
