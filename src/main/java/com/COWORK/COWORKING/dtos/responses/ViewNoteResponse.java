package com.COWORK.COWORKING.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewNoteResponse {
    private Long noteId;
    private String content;
}
