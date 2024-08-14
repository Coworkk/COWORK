package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllUserTasksInProjectRequest {
    private String userId;
    private Long projectId;
}
