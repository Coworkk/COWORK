package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.responses.AttachNoteResponse;
import com.COWORK.COWORKING.dtos.responses.ViewNoteResponse;

public interface NoteService {
    AttachNoteResponse attachNote(AttachNoteRequest attachNoteRequest);

    ViewNoteResponse viewNote(Long noteId);

    String deleteNote(Long noteId);
}
