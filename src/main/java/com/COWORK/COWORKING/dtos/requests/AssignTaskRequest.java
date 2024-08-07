package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignTaskRequest {
    private Long taskId;
    private Long userId;
}
