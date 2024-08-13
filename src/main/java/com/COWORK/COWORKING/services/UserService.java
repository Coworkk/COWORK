package com.COWORK.COWORKING.services;


import com.COWORK.COWORKING.dto.*;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    void sendVerificationEmail(String userId);

    RoleResponse assignRole(String userId, String roleName);

    RoleResponse addARoleToProject(String roleName);

    ResetPasswordResponse resetPassword(String username);

    ResponseEntity<?> logIn(LogInRequest logInRequest);

    ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
