package com.COWORK.COWORKING.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ViewAllUserTasksByDueDateRequest {
    private Long userId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dueDate;
}
