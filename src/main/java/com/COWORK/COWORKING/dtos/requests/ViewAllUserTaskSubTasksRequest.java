package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllUserTaskSubTasksRequest {
    private String userId;
    private Long taskId;
}
