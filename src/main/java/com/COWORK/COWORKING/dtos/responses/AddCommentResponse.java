package com.COWORK.COWORKING.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AddCommentResponse {
    private String comment;
    private Long userId;
    private Long taskId;
    private String message;
}
