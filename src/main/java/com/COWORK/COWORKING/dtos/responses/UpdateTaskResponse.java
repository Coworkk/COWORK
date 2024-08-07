package com.COWORK.COWORKING.dtos.responses;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateTaskResponse {
    private Long taskId;
    private String message;
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime dateUpdated;
    private Long projectId;
    private String title;
    private String description;

}
