package com.COWORK.COWORKING.services;


import com.COWORK.COWORKING.dto.ResetPasswordResponse;
import com.COWORK.COWORKING.dto.RoleResponse;
import com.COWORK.COWORKING.dto.UserRequest;
import com.COWORK.COWORKING.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    void sendVerificationEmail(String userId);

    RoleResponse assignRole(String userId, String roleName);

    RoleResponse addARoleToProject(String roleName);

    ResetPasswordResponse resetPassword(String username);
}
