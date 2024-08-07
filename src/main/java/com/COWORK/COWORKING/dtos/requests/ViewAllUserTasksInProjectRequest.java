package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllUserTasksInProjectRequest {
    private Long userId;
    private Long projectId;
}
