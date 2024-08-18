package com.COWORK.COWORKING.dtos.responses;

import com.COWORK.COWORKING.data.models.Status;
import com.COWORK.COWORKING.data.models.Task;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ViewSubTaskResponse {
    private Long subTaskId;
    private String title;
    private String description;
//    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime dateCreated;
//    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime dateUpdated;
//    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime startDate;
//    @JsonSerialize(using = JsonSerializer.class)
    private LocalDateTime dueDate;
    private Status status;
    private Long taskId; // edit this for frontend
}
