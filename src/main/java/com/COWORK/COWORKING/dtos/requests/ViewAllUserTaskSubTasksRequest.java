package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllUserTaskSubTasksRequest {
    private Long userId;
    private Long taskId;
}
