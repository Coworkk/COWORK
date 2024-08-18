package com.COWORK.COWORKING.controllers;
import com.COWORK.COWORKING.data.models.User;
import com.COWORK.COWORKING.dtos.requests.LogInRequest;
import com.COWORK.COWORKING.dtos.requests.RefreshTokenRequest;
import com.COWORK.COWORKING.dtos.requests.UserRequest;
import com.COWORK.COWORKING.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cowork")
public class UserControllers {
    private final UserServicesImpl userServices;
  @PostMapping("users/register")
  public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
      try {
          return new ResponseEntity<>(userServices.createUser(userRequest), CREATED);
      }catch (Exception exception){
          return new ResponseEntity<>(exception.getMessage(), EXPECTATION_FAILED);
      }
  }
//    @AuthenticationPrincipal User user
 @PutMapping("/users/{id}/send-email")
 public ResponseEntity<?> sendEmail(@PathVariable  String id){
      userServices.sendVerificationEmail(id);
       return ResponseEntity.ok().build();
 }

 @PutMapping("/admin/add-new-role-to-project")
 public ResponseEntity<?> addRole(@RequestParam String roleName){
      try {
          return new ResponseEntity<>(userServices.addARoleToProject(roleName), CREATED);
      }catch (Exception exception){
          return new ResponseEntity<>("role already exist ", EXPECTATION_FAILED);
      }
 }


 @PutMapping("/admin/assignRoles/{id}")
 public ResponseEntity<?> assignRole(@PathVariable String id , @RequestParam String role){
        return  new ResponseEntity<>(userServices.assignRole(id, role),OK);
 }


 @PutMapping("/users/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String username){
      return new ResponseEntity<>(userServices.resetPassword(username), OK);
 }

 @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LogInRequest logInRequest){
      return new ResponseEntity<>(userServices.logIn(logInRequest),OK );
 }

 @PostMapping("/admin/refreshToken")
 public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
     return new ResponseEntity<>(userServices.refreshToken(refreshTokenRequest),OK );
 }
}
