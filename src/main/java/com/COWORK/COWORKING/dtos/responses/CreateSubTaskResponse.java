package com.COWORK.COWORKING.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateSubTaskResponse {
    private Long subTaskId;
    private String message;
    private LocalDateTime dateCreated;
    private Long taskId;

}
