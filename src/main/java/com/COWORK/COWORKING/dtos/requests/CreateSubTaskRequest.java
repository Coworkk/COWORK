package com.COWORK.COWORKING.dtos.requests;

import com.COWORK.COWORKING.data.models.Status;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateSubTaskRequest {
    private String title;
    private String description;
    private Status status;
    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime dateCreated;
    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime dateUpdated;
    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime dueDate;
    private Long taskId;
}
