package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/attachNote")
    public ResponseEntity<?> attachNote(@RequestBody AttachNoteRequest attachNoteRequest) {
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(noteService.attachNote(attachNoteRequest), true));
    }

//    @PatchMapping("/updateNote")
//    public ResponseEntity<?> updateNote(@RequestBody ) {
//        return ResponseEntity.status(OK)
//                .body(new ApiResponse(noteService.updateNote(), true));
//    }

    @GetMapping("/viewNote/{noteId}")
    public ResponseEntity<?> viewNote(@PathVariable Long noteId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewNote(noteId), true));
    }

    @GetMapping("/viewAllProjectNotes")
    public ResponseEntity<?> viewProjectNotes(@RequestParam Long projectId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewAllProjectNotes(projectId), true));
    }

    @GetMapping("/viewAllUserNotes")
    public ResponseEntity<?> viewAllUserNotes(@RequestParam Long userId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewAllUserNotes(userId), true));
    }

    @DeleteMapping("/deleteNote/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long noteId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewNote(noteId), true));
    }
}
