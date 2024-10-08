package com.COWORK.COWORKING.dtos.responses;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AttachNoteResponse {
    private Long noteId;
    private String message;
    private Long projectId;
//    @JsonSerialize(using = JsonSerializer.class)
//    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime timeAttached;
}
