package com.COWORK.COWORKING.dtos.requests;

import com.COWORK.COWORKING.data.models.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllUserSubTasksByStatusRequest {
    private Long userId;
    private Status status;
}
