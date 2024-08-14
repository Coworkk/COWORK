package com.COWORK.COWORKING.dtos.requests;

import com.COWORK.COWORKING.data.models.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllUserTasksByStatusRequest {
    private Status status;
    private String userId;
}
