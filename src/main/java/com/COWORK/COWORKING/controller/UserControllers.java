package com.COWORK.COWORKING.controller;
import com.COWORK.COWORKING.dto.UserRequest;
import com.COWORK.COWORKING.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllers {
    private final UserServicesImpl userServices;
  @PostMapping
  public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
      try {
          return new ResponseEntity<>(userServices.createUser(userRequest), CREATED);
      }catch (Exception exception){
          return new ResponseEntity<>(exception.getMessage(), EXPECTATION_FAILED);
      }
  }

 @PutMapping("/{id}/send-email")
 public ResponseEntity<?> sendEmail(@PathVariable String id){
      userServices.sendVerificationEmail(id);
       return ResponseEntity.ok().build();
 }

 @PutMapping("/add-new-role-to-project")
 @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<?> addRole(@RequestParam String roleName){
      try {
          return new ResponseEntity<>(userServices.addARoleToProject(roleName), CREATED);
      }catch (Exception exception){
          return new ResponseEntity<>("role already exist ", EXPECTATION_FAILED);
      }
 }

 @PutMapping("assignRoles/{id}")
 public ResponseEntity<?> assignRole(@PathVariable String id , @RequestParam String role){
        return  new ResponseEntity<>(userServices.assignRole(id, role),OK);
 }


 @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String username){
      return new ResponseEntity<>(userServices.resetPassword(username), OK);
 }


 

}
