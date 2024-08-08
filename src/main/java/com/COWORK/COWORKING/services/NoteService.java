package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.requests.ViewAllProjectNotesRequest;
import com.COWORK.COWORKING.dtos.responses.AttachNoteResponse;
import com.COWORK.COWORKING.dtos.responses.ViewNoteResponse;

import java.util.List;

public interface NoteService {
    AttachNoteResponse attachNote(AttachNoteRequest attachNoteRequest);

    ViewNoteResponse viewNote(Long noteId);

    List<ViewNoteResponse> viewAllProjectNotes(Long projectId);

    String deleteNote(Long noteId);
}
