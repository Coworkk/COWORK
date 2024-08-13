package com.COWORK.COWORKING.controllers;

import com.COWORK.COWORKING.dtos.requests.AttachNoteRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/cowork")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("users/attachNote")
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

    @GetMapping("users/viewAllProjectNotes")
    public ResponseEntity<?> viewProjectNotes(@RequestParam Long projectId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewAllProjectNotes(projectId), true));
    }

    @GetMapping("users/viewAllUserNotes")
    public ResponseEntity<?> viewAllUserNotes(@RequestParam Long userId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewAllUserNotes(userId), true));
    }

    @DeleteMapping("users/deleteNote/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long noteId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(noteService.viewNote(noteId), true));
    }
}
