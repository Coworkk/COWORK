package com.COWORK.COWORKING.services.impl;


import com.COWORK.COWORKING.dto.ResetPasswordResponse;
import com.COWORK.COWORKING.dto.RoleResponse;
import com.COWORK.COWORKING.dto.UserRequest;
import com.COWORK.COWORKING.dto.UserResponse;
import com.COWORK.COWORKING.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServicesImpl implements UserService {
    @Value("${app.keycloak.realm}")
    private  String realm;
    private final Keycloak keycloak;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserRepresentation userRepresentation = getUserRepresentation(userRequest);
        UsersResource usersResource = getUserResources();
        getUserResources().create(userRepresentation);
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(userRequest.getUsername(), true);
        UserRepresentation user = userRepresentations.get(0);
        sendVerificationEmail(user.getId());
        return UserResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName())
        .id(user.getId()).username(user.getUsername()).build();
    }

    private UserRepresentation getUserRepresentation(UserRequest userRequest) {
        if(!getUserResources().search(userRequest.getUsername()).isEmpty()) {
            throw  new RuntimeException("user already exist exception");   }
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(userRequest.getFirstName());
        userRepresentation.setLastName(userRequest.getLastName());
        userRepresentation.setUsername(userRequest.getUsername());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEmail(userRequest.getUsername());
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRequest.getPassword());
        credentialRepresentation.setType(PASSWORD);
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        return userRepresentation;
    }

    @Override
    public void sendVerificationEmail(String userId) {
        UsersResource usersResource = getUserResources();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public ResetPasswordResponse resetPassword(String username){
        UsersResource usersResource = getUserResources();
       List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username,false);
       UserRepresentation userRepresentation = userRepresentations.get(0);
       UserResource resource = usersResource.get(userRepresentation.getId());
       resource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
       return ResetPasswordResponse.builder().message("the link to reset your password has been sent to "+username).build();
    }
    @Override
    public RoleResponse assignRole(String userId, String roleName) {
        UsersResource usersResource = getUserResources();
        RoleRepresentation representation = getRoleResources().get(roleName).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(representation));
        return RoleResponse.builder().message("role successfully assigned").build();
    }



    public RoleResponse addARoleToProject(String roleName) {
            getRoleResources().create(new RoleRepresentation(roleName, roleName.toLowerCase() + " role", true));
            return RoleResponse.builder().message("Role successfully created").build();
    }




    private RolesResource getRoleResources(){return keycloak.realm(realm).roles();}
    private UsersResource getUserResources(){return keycloak.realm(realm).users();}
}
