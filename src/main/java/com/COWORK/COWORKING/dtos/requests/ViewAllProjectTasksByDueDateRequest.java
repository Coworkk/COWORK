package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ViewAllProjectTasksByDueDateRequest {
    private Long projectId;
    private LocalDateTime dueDate;
}
