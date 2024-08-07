package com.COWORK.COWORKING.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttachNoteResponse {
    private Long noteId;
    private String message;
    private Long projectId;
}
