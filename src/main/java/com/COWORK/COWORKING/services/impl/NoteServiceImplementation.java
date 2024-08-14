package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.Note;
import com.COWORK.COWORKING.data.models.Project;
import com.COWORK.COWORKING.data.models.User;
import com.COWORK.COWORKING.data.repositories.NoteRepository;
import com.COWORK.COWORKING.data.repositories.UserRepository;
import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.responses.AttachNoteResponse;
import com.COWORK.COWORKING.dtos.responses.ViewNoteResponse;
import com.COWORK.COWORKING.exceptions.NoteNotFoundException;
import com.COWORK.COWORKING.services.NoteService;
import com.COWORK.COWORKING.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImplementation implements NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;
    private ProjectService projectService;
    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public AttachNoteResponse attachNote(AttachNoteRequest attachNoteRequest) {
        Project project = projectService.findProjectById(attachNoteRequest.getProjectId());
        User user  = userRepository.getUserByUserId(attachNoteRequest.getUserId());
        if(user == null) throw new RuntimeException("user not found");
        Note note = Note.builder()
                .content(attachNoteRequest.getContent())
                .project(project)
                .user(user)
                .build();
        noteRepository.save(note);
        AttachNoteResponse attachNoteResponse = modelMapper.map(note, AttachNoteResponse.class);
        attachNoteResponse.setMessage("Note attached successfully");
        return attachNoteResponse;
    }

    public Note findNoteById(Long noteId){
        return noteRepository.findById(noteId)
                .orElseThrow(()-> new NoteNotFoundException(
                        String.format("Note with id %d not found", noteId)
                ));
    }

    @Override
    public ViewNoteResponse viewNote(Long noteId) {
        Note note = findNoteById(noteId);
        return modelMapper.map(note, ViewNoteResponse.class);
    }

    @Override
    public List<ViewNoteResponse> viewAllProjectNotes(Long projectId) {
        projectService.findProjectById(projectId);
        List<Note> notes = noteRepository.findNoteByProjectId(projectId);
        return notes.stream()
                .map(projectNote -> modelMapper.map(projectNote, ViewNoteResponse.class)).toList();
    }

    @Override
    public List<ViewNoteResponse> viewAllUserNotes(String userId) {
        User user = userRepository.getUserByUserId(userId);
        if(user==null)throw new RuntimeException("user not found");
        List<Note> notes = noteRepository.findNoteByUserId(userId);
        return notes.stream()
                .map(userNote -> modelMapper.map(userNote, ViewNoteResponse.class)).toList();
    }

    @Override
    public String deleteNote(Long noteId) {
        Note note = findNoteById(noteId);
        noteRepository.delete(note);
        return "Note deleted successfully";
    }
}
