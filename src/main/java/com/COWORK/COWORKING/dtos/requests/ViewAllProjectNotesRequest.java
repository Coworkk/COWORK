package com.COWORK.COWORKING.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAllProjectNotesRequest {
    private Long noteId;
    private Long projectId;
}
