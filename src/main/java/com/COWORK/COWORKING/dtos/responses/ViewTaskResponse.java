package com.COWORK.COWORKING.dtos.responses;

import com.COWORK.COWORKING.data.models.Priority;
import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.models.Status;
import com.COWORK.COWORKING.data.models.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ViewTaskResponse {
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateCreated;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateUpdated;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dueDate;
    private User user;
    private Project project;
    private Priority priority;

}
