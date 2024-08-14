package com.COWORK.COWORKING.dtos.responses;

import com.COWORK.COWORKING.data.models.Priority;
import com.COWORK.COWORKING.data.models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskResponse {
    private Long taskId;
    private String title;
    private String description;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dueDate;
    private Priority priority;
    private Status status;
}
