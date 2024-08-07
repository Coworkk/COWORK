package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttachNoteRequest {
    private String description;
    private Long projectId;
    private Long userId;
}
