package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/note")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/attachNote")
    public ResponseEntity<?> attachNote(@RequestBody AttachNoteRequest attachNoteRequest) {
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(noteService.attachNote(attachNoteRequest), true));
    }

    @GetMapping
    public ResponseEntity<?> viewNote()
}
