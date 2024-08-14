package com.COWORK.COWORKING.dtos.responses;

import com.COWORK.COWORKING.data.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AddCommentResponse {
    private String comment;
    private String commenterId;
    private Long taskId;
    private String message;
}
