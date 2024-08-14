package com.COWORK.COWORKING.controllers;


import com.COWORK.COWORKING.dtos.requests.AddCommentRequest;
import com.COWORK.COWORKING.dtos.requests.EditCommentRequest;
import com.COWORK.COWORKING.dtos.responses.ApiResponse;
import com.COWORK.COWORKING.services.CommentService;
import jakarta.ws.rs.Consumes;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/cowork")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "users/addComment",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest addCommentRequest) {
      try {
          return ResponseEntity.status(CREATED)
                  .body(new ApiResponse(commentService.addComment(addCommentRequest), true));
      }catch (Exception e){
          return ResponseEntity.status(EXPECTATION_FAILED)
                  .body(new ApiResponse("something went wrong ", false));
      }
    }

    @PatchMapping("users/editComment")
    public ResponseEntity<?> editComment(@RequestBody EditCommentRequest editCommentRequest) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(commentService.editComment(editCommentRequest), true));
    }

    @GetMapping("/viewAllTaskComments")
    public ResponseEntity<?> viewAllTaskComments(@RequestParam Long taskId) {
        return ResponseEntity.status(OK)
                .body(new ApiResponse(commentService.viewAllTaskComments(taskId), true));
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
//        return ResponseEntity.status(OK)
//                .body(new ApiResponse(commentService.deleteComment(commentId), true));
        return null;
    }
}
