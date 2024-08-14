package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCommentRequest {
    private Long taskId;
    private String comment;
    private String UserId;
}
