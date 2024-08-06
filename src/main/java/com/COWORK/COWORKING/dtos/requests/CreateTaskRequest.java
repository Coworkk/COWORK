package com.COWORK.COWORKING.dtos.requests;

import com.COWORK.COWORKING.data.models.Priority;
import com.COWORK.COWORKING.data.models.Status;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateTaskRequest {
    private String title;
    private String description;
    private Status status;
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime dueDate;
    private Long projectId;
    private Priority priority;
}
