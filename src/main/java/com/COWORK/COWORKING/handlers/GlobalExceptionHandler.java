package com.COWORK.COWORKING.handlers;

import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.exceptions.NoteNotFoundException;
import com.COWORK.COWORKING.exceptions.SubTaskNotFoundException;
import com.COWORK.COWORKING.exceptions.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> taskNotFoundHandler(TaskNotFoundException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }

    @ExceptionHandler(SubTaskNotFoundException.class)
    public ResponseEntity<?> subTaskNotFoundHandler(SubTaskNotFoundException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<?> noteNotFoundHandler(NoteNotFoundException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }
}
